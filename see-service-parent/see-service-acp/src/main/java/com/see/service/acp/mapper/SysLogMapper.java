package com.see.service.acp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.see.service.acp.model.SysLog;

@Mapper
public interface SysLogMapper {

	SysLog selectByPrimaryKey(Integer slgId);

}