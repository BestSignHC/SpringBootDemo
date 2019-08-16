package com.hecheng.mapper.cluster;

import com.hecheng.domain.IntAlias;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IntAliasMapper {

    IntAlias findById(@Param("aliasId") Long aliasId);
}
