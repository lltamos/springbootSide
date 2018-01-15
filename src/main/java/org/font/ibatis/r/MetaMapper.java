package org.font.ibatis.r;


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.font.ibatis.User;

import java.util.Date;
import java.util.List;

@Mapper
public interface MetaMapper {
    @SelectProvider(type = MetaSelectProvider.class, method = "xxxQueryById")
    @Results(value = {
            @Result(id = true, property = "id", column = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "gmtCreate", column = "gmt_create", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)})
    List<User> xxxQueryById(@Param("xxxId") String xxxId);
}
