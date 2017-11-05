package com.itjuzi.mapper;

import com.itjuzi.entity.CrawerCompanyInnoItjuzi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrawerCompanyInnoItjuziMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawerCompanyInnoItjuzi record);

    int insertSelective(CrawerCompanyInnoItjuzi record);

    CrawerCompanyInnoItjuzi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawerCompanyInnoItjuzi record);

    int updateByPrimaryKeyWithBLOBs(CrawerCompanyInnoItjuzi record);

    int updateByPrimaryKey(CrawerCompanyInnoItjuzi record);
}