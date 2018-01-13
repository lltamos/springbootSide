package org.font.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Cacheable {

    String key() default ""; // 缓存key

    ExpireTime expire() default ExpireTime.NONE; // 缓存时效,默认无限期

}
