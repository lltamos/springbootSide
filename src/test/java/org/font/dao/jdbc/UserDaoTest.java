package org.font.dao.jdbc;

import org.font.DemoApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class,DemoApplication.class})
@WebAppConfiguration
public class UserDaoTest {
    @Autowired
    private UserDao userService;

    @Before
    public void setUp() {
        // 准备，清空user表
        userService.deleteAllUsers();
    }

    @Test
    public void test() throws Exception {
        // 插入5个用户
        userService.create("a", 1);
        userService.create("b", 2);
        userService.create("c", 3);
        userService.create("d", 4);
        userService.create("e", 5);

        // 查数据库，应该有5个用户
        int count = userService.getAllUsers().intValue();

        System.out.println("用户总数"+count);

        // 删除两个用户
        userService.deleteByName("a");
        userService.deleteByName("e");

    }
}
