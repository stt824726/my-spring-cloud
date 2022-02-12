package com.stt.common.aop;

import java.lang.annotation.*;

/**
 *
 * 注解式缓存使用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface QueryCache {

    /**
     * 缓存key
     * @return
     */
    String value() default "";

    /**
     * 缓存时间
     * @return
     */
    int duration() default 60*30;

    /**
     * 缓存key前缀
     * @return
     */
    String pkey() default "";

}
