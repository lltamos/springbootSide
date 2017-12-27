package org.font.dao.jpa;

import org.font.DemoApplication;
import org.font.bean.User;
import org.font.dao.jpa.scan.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class, DemoApplication.class})
@WebAppConfiguration
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {

        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        List<User> fff = userRepository.findByName("FFF");
        System.out.println(fff.size());
        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        System.out.println("剩余：" + userRepository.findAll().size());

    }
}
