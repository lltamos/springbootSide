package org.font.redis;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.surefire.util.ReflectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(cache)")
    public Object cacheable(final ProceedingJoinPoint pjp, Cacheable cache) throws Throwable {

        String key = getCacheKey(pjp, cache.key());
//		 //方案一：使用自定义缓存工具类操作缓存
//		 Object value = CacheUtils.getObj(key);// 从缓存获取数据
//		 if (value != null) {
//		 return value; // 如果有数据,则直接返回
//		 }
//		 value = pjp.proceed(); // 缓存,到后端查询数据
//		 if (value != null) {
//		 CacheUtils.set(key, value, cache.expire());
//		 }

        // 方案二：使用redisTemplate操作缓存
        @SuppressWarnings("unchecked")
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        Object value = valueOper.get(key); // 从缓存获取数据
        if (value != null) {
            return value; // 如果有数据,则直接返回
        }

        value = pjp.proceed(); // 缓存,到后端查询数据
        CacheUtils.set(key, value, cache.expire());
        if (cache.expire().getTime() <= 0) { // 如果没有设置过期时间,则无限期缓存
            valueOper.set(key, value);
        } else { // 否则设置缓存时间
            valueOper.set(key, value, cache.expire().getTime(), TimeUnit.SECONDS);
        }
        return value;
    }

    @Around("@annotation(evict)")
    public Object cacheEvict(final ProceedingJoinPoint pjp, CacheEvict evict) throws Throwable {
        Object value = pjp.proceed(); // 执行方法
        String key = getCacheKey(pjp, evict.key());
//		 //方案一：使用自定义缓存工具类操作缓存
//		 CacheUtils.del(key);

        // 方案二：使用redisTemplate操作缓存
        if (evict.key().equals(key)) {// 支持批量删除
            Set<String> keys = redisTemplate.keys(key.concat("*"));
            redisTemplate.delete(keys);
        } else {
            redisTemplate.delete(key);
        }
        return value;
    }


    /**
     * 获取缓存的key值
     *
     * @param pjp
     * @param key
     * @return
     */
    private String getCacheKey(ProceedingJoinPoint pjp, String key) {

        StringBuilder buf = new StringBuilder();
        Object[] args = pjp.getArgs();

        if (StringUtils.isNotBlank(key)) {
            buf.append(key);
            List<String> annoParamNames = AopUtils.getAnnoParams(key);
            String[] methodParamNames = AopUtils.getMethodParamNames(AopUtils.getMethod(pjp));
            if (!CollectionUtils.isEmpty(annoParamNames)) {
                for (String ap : annoParamNames) {
                    String paramValue = "";
                    for (int i = 0; i < methodParamNames.length; i++) {
                        if (ap.startsWith(methodParamNames[i])) {
                            Object arg = args[i];
                            if (ap.contains(".")) {
                                paramValue = String.valueOf(ReflectionUtils.invokeGetter(arg, ap.substring(ap.indexOf(".") + 1)));
                            } else {
                                paramValue = String.valueOf(arg);
                            }
                        }
                    }
                    int start = buf.indexOf("{" + ap);
                    int end = start + ap.length() + 2;
                    buf = buf.replace(start, end, paramValue);
                }
            }

        } else {
            buf.append(pjp.getSignature().getDeclaringTypeName()).append(":").append(pjp.getSignature().getName());
            for (Object arg : args) {
                buf.append(":").append(arg.toString());
            }
        }

        return buf.toString();
    }
}