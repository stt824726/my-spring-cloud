package com.stt.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stt.constant.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * @Description 以注解为缓存切入点，实现注解式缓存使用
 * @Author shaotongtong
 * @Date 2022-02-12 17:45
 */
@Aspect
@Service
@Slf4j
public class DBCacheAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private Environment env;

    /**
     * 定义拦截规则：拦截所有@QueryCache注解的方法。
     */
    @Pointcut("@annotation(com.stt.common.aop.QueryCache)")
    public void queryCachePointcut() {

    }

    /**
     * 拦截器具体实现
     */
    @Around("queryCachePointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        log.debug("AOP 缓存切面处理 >>>> start ");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取被拦截的方法
        Method method = signature.getMethod();
        QueryCache annotation = method.getAnnotation(QueryCache.class);
        String cacheKey = annotation.value();
        String pkey = annotation.pkey();
        int duration = annotation.duration();
        if (StringUtils.isNotBlank(pkey)) {
            Integer d = env.getProperty(pkey, Integer.class);
            if (d != null) {
                duration = d;
            }
        }
        String key = method.toString();
        if (StringUtils.isNotEmpty(cacheKey)) {
            key = cacheKey;
        }

        for (Object value : pjp.getArgs()) {
            key += "_" + new ObjectMapper().writeValueAsString(value);
        }

        log.debug("获取到缓存key值 >>>> " + key);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Object object = operations.get(key);
        if (object != null) {
            // 缓存中获取到数据，直接返回。
            log.debug("从缓存中获取到数据 >>>> " + object.toString());
            log.debug("AOP 缓存切面处理 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime));
            return object;
        }
        // 缓存中没有数据，调用原始方法查询数据库
        object = pjp.proceed();
        if (object instanceof Result) {
            Result result = (Result) object;
            if(Result.SUCCESS_CODE == result.getResult()){
                //如果返回值为Result对象 则Result成功才缓存结果数据 不为Result的不判断
                operations.set(key, object, duration, TimeUnit.SECONDS);
            }
        }else{
            operations.set(key, object, duration, TimeUnit.SECONDS);
        }
        log.debug("DB取到数据并存入缓存 >>>> " + object.toString());
        log.debug("AOP 缓存切面处理 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime));
        return object;
    }

}
