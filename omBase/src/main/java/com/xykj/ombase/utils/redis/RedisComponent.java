package com.xykj.ombase.utils.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ocean
 * @Title: RedisComponent
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午9:59
 */
@Component
public class RedisComponent implements IRedisClient {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    /**
     * 设置缓存 key+value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取缓存 key
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean expire(String key, int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * hash获取
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public void hset(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * hash get
     *
     * @param key
     * @param field
     * @return
     */
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * hash 删除
     *
     * @param key
     * @param field
     * @return
     */
    public Long hdel(String key, String... field) {
        return redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * hash 是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * hash 取 key
     *
     * @param key
     * @return
     */
    public List<Object> hvals(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 普通删除
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }


}
