package org.font.ibatis.r;

import java.util.Map;

public class MetaSelectProvider {
    private static final String xxxSQL = "id,name";

    public String xxxQueryById(Map<String, Object> parameter) {
        org.apache.ibatis.jdbc.SQL sql = new org.apache.ibatis.jdbc.SQL();
        sql.SELECT(xxxSQL).FROM("table_name").WHERE("biz_id=#{xxxId}");
        return sql.toString();
    }
}
