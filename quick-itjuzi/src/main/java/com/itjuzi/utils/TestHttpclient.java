package com.itjuzi.utils;

import com.google.common.base.Joiner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestHttpclient {
	
	// 定义申请获得的appKey和appSecret
	public final static  String appkey = "27888787";
	public final static  String secret = "da02180a083cdbcb629406cfa5bd610a";
	public final static  String proxyIP = "s5.proxy.mayidaili.com";
	public final static  int proxyPort = 8123;


	public String getAuthHeader() {
		
		// 创建参数表
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("app_key", appkey);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 使用中国时间，以免时区不同导致认证错误
		paramMap.put("timestamp", format.format(new Date()));
		
		// 对参数名进行排序
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		
		// 拼接有序的参数名-值串
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(secret);
		for (String key : keyArray) {
			stringBuilder.append(key).append(paramMap.get(key));
		}
		
		stringBuilder.append(secret);
		String codes = stringBuilder.toString();
		
		// MD5编码并转为大写， 这里使用的是Apache codec
		String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(codes).toUpperCase();
		
		paramMap.put("sign", sign);
		
		// 拼装请求头Proxy-Authorization的值，这里使用 guava 进行map的拼接
		String authHeader = "MYH-AUTH-MD5 " + Joiner.on('&').withKeyValueSeparator("=").join(paramMap);
		
		System.out.println(authHeader);
		
		return authHeader;
	}
	
	public void ip() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException {
		
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				return true;
			}
		}).build();
		
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);
		
		HttpClientBuilder builder = HttpClients.custom();
		builder.setSSLSocketFactory(sslsf);
		
		SocketConfig.Builder configBuilder = SocketConfig.custom();
		configBuilder.setSoReuseAddress(true);
		builder.setDefaultSocketConfig(configBuilder.build());
		
		CloseableHttpClient httpclient = builder.build();
		
		try {
			HttpGet httpget = new HttpGet("http://1212.ip138.com/ic.asp");//
			HttpHost proxy = new HttpHost(proxyIP, proxyPort, "http");
			RequestConfig config = RequestConfig.custom().setProxy(proxy).setRedirectsEnabled(false).build();
			httpget.setConfig(config);
			
			httpget.setHeader("Proxy-Authorization", getAuthHeader());
			
			System.out.println("executing request " + httpget.getRequestLine());
			
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				System.out.println(Arrays.toString(response.getAllHeaders()));
				
				System.out.println(EntityUtils.toString(entity, "GB2312"));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		
	}
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			ClientProtocolException, IOException {
		new TestHttpclient().ip();
	}
}
