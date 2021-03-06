package com.stt.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> template;

    private static RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate = template;
    }

    public static void removeObject(String key) {
        log.warn("remove : {}", key);
        redisTemplate.delete(key);
    }

    public static void removeLike(String key) {
        log.warn("redis remove like key: {}", key);
        Set<String> keys = redisTemplate.keys(key + ":*");
        redisTemplate.delete(keys);
    }

    public static ValueOperations<String, Object> setObject(String key, Object object, long duration) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        operation.set(key, object, duration, TimeUnit.SECONDS);
        return operation;
    }

    public static ValueOperations<String, Object> setObject(String key, Object object) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        operation.set(key, object);
        return operation;
    }

    public static Object getObject(String key) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }


    public static String getString(String key) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        return operation.get(key) == null? null:operation.get(key).toString();
    }

    public static Object setOperationAdd(String key,Object object) {
        SetOperations<String,Object> setOperations=redisTemplate.opsForSet();
        //??????????????????????????????,????????????
        return setOperations.add(key,object);
    }
    public static Object setOperationGet(String key) {
        SetOperations<String,Object> setOperations=redisTemplate.opsForSet();
        //????????????????????????????????????????????????????????????
        return setOperations.pop(key);
    }

    public static Long leftPushAll(String key, Collection<Object> values) {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        //redis????????????
        return listOperations.leftPushAll(key, values);
    }

    @SuppressWarnings("unchecked")
    public static Long rightPushAll(String key, Collection values) {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        //redis????????????,????????????
        return listOperations.rightPushAll(key, values);
    }

    public static Long listSize(String key) {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        //redis????????????
        return listOperations.size(key);
    }

    public static List rangeList(String key, long start, long end){
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        return listOperations.range(key,start,end);
    }

    public static Boolean expireObject(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public static void pipeAdd(String key, List<Long> values, Long expire) {
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisConnection stringRedisConn = (RedisConnection) connection;
                values.forEach(x->{
                    stringRedisConn.setCommands().sAdd(key.getBytes(), String.valueOf(x).getBytes());
                });
                return null;
            }
        });
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * ????????????????????????????????????hash?????????
     * filed?????????????????????field???????????????field???????????????
     * @param hash
     * @param hashKey
     * @param hashValue
     */
    public static void hashOperationAdd(String hash, Object hashKey, Object hashValue) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(hash, hashKey, hashValue);
    }

    /**
     * ???????????????hash??????????????????????????????????????????????????????????????????
     * @param hash
     * @param hashKey
     * @return
     */
    public static Object hashOperationGetAndDelete(String hash, Object hashKey) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Object object = hashOperations.get(hash, hashKey);
        if (object != null) {
            hashOperations.delete(hash,hashKey);
        }
        return object;
    }

    /**
     * ??????????????????????????????
     * @param hash
     * @param hashKey
     * @return
     */
    public static Boolean hasHashKey(String hash, Object hashKey) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.hasKey(hash, hashKey);
    }

    /**
     * ???????????????hash????????????????????????????????????
     * @param hash
     * @param hashKey
     * @return
     */
    public static Object hashOperationGet(String hash, Object hashKey) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Object object = hashOperations.get(hash, hashKey);
        return object;
    }

}
