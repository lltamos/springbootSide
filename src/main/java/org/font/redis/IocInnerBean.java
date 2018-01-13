package org.font.redis;

import org.springframework.stereotype.Component;

@Component
public class IocInnerBean {

    public String print() {
        return "IocBean";
    }

    @Cacheable(expire = ExpireTime.HALF_A_MIN)
    public String get(int i) {
        System.out.println("执行了get");
        return i + "";
    }

}
