package com.see.service.acp.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.see.service.acp.core.model.SysLog;

@Mapper
public interface SysLogMapper {

	SysLog selectByPrimaryKey(Integer slgId);

}