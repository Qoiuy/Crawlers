package com.itjuzi.mapper;

import com.itjuzi.entity.CrawerCompanyInnoItjuziSeed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CrawerCompanyInnoItjuziSeedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawerCompanyInnoItjuziSeed record);

    int insertSelective(CrawerCompanyInnoItjuziSeed record);

    CrawerCompanyInnoItjuziSeed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawerCompanyInnoItjuziSeed record);

    int updateByPrimaryKey(CrawerCompanyInnoItjuziSeed record);

    int countByurl(@Param("url") String url);

    List<CrawerCompanyInnoItjuziSeed> selectList();

    List<CrawerCompanyInnoItjuziSeed> selectListOrderById();

    CrawerCompanyInnoItjuziSeed selectOneSeed();
}
