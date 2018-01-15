package org.font.ibatis;

import com.alibaba.fastjson.JSON;
import org.font.DemoApplication;
import org.font.controller.rest.HelloController;
import org.font.properties.BlogProperties;
import org.font.redis.IocBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class, DemoApplication.class})
@WebAppConfiguration
public class IbatisTests {

    @Autowired
    private UserMapper userMapper;

    @Test
//    @Rollback
    public void findByName() throws Exception {
        userMapper.insert("AAA", 20);
        List<User> users = userMapper.findByName("AAA");
        System.out.println(JSON.toJSONString(users));
    }

}
