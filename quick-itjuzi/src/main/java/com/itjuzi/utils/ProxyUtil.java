package com.itjuzi.utils;

import com.google.common.base.Joiner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/2
 * Time: 14:20
 * Description:
 */
public class ProxyUtil {


    public final static  String appkey = "27888787";
    public final static  String secret = "da02180a083cdbcb629406cfa5bd610a";
    public final static  String proxyIP = "s5.proxy.mayidaili.com";
    public final static  int proxyPort = 8123;


    public static String getAuthHeader() {

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
        return authHeader;
    }

//    public void ip() {
//
//        try {
//            Document doc = Jsoup.connect("https://www.itjuzi.com/").header("Proxy-Authorization", getAuthHeader())
//                    .proxy(proxyIP, proxyPort).followRedirects(false).validateTLSCertificates(false).timeout(10000).get();
//            System.out.println(doc.title());
//            String text = doc.select("div").text();
//            int ipStart = text.indexOf("[");
//            int ipEnd = text.lastIndexOf("]");
//            String ip = text.substring(ipStart + 1, ipEnd);
//            System.out.println(ip);
////
//        } catch (IOException e) {
//////             TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }


}
