package org.font.redis;

public class IocBean {

    private static IocInnerBean innerBean = SpringContextHolder.getBean(IocInnerBean.class);

    public static String print() {
        return innerBean.print();
    }

    public static String get(int i) {

        return innerBean.get(i);
    }

}
