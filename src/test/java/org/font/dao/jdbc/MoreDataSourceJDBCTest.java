package org.font.dao.jdbc;

import org.font.DemoApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class, DemoApplication.class})
@WebAppConfiguration
public class MoreDataSourceJDBCTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Before
    public void setUp() {
//        jdbcTemplate1.update("DELETE  FROM  USER ");
//        jdbcTemplate2.update("DELETE  FROM  USER ");
    }

    @Test
    public void test() throws Exception {


        System.out.println(1);
        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update("INSERT INTO user(id,name,age) VALUES(?, ?, ?)", 1, "aaa", 20);
        jdbcTemplate1.update("INSERT INTO user(id,name,age) VALUES(?, ?, ?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("INSERT INTO user(id,name,age) VALUES(?, ?, ?)", 1, "aaa", 20);


    }


}
