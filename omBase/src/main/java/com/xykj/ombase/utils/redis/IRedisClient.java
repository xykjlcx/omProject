package com.xykj.ombase.utils.redis;

import java.util.List;

/**
 * @author ocean
 * @Title: IRedisClient
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/19上午9:58
 */
public interface IRedisClient {

    void set(String key, String value);

    String get(String key);

    boolean exists(String key);

    boolean expire(String key, int seconds);

    void hset(String key, String field, String value);

    Object hget(String key, String field);

    Long hdel(String key, String... field);

    boolean hexists(String key, String field);

    List<Object> hvals(String key);

    void del(String key);

}
