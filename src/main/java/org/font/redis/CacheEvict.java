package org.font.redis;

import java.lang.annotation.*;

/**
 * 缓存清除
 *
 * @author lh
 * @version 3.0
 * @since 2016-8-28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheEvict {

    String key() default "";// 缓存key

}
