package com.xiaohuis.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p> Redis工具类 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/9 23:32
 */

@Slf4j
@Component
public class RedisCache {

    private final StringRedisTemplate stringRedisTemplate;


    public RedisCache(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public void set(String key, Object value){
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param time 过期时间
     * @param unit 时间单位
     */
    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value), time, unit);
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param time 过期时间
     * @param unit 时间单位
     */
    public void setString(String key, String value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 设置有效时间
     * @param key Redis键
     * @param time 时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(String key, long time, TimeUnit unit)
    {
        return stringRedisTemplate.expire(key, time, unit);
    }

    /**
     * 获取有效时间
     * @param key Redis键
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public Long getExpire(String key, TimeUnit unit)
    {
        return stringRedisTemplate.getExpire(key, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T get(String key, Class<T> clazz){
        String value = stringRedisTemplate.opsForValue().get(key);
        return JSON.parseObject(value, clazz);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public String getString(String key){
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean delete(String key)
    {
        return stringRedisTemplate.delete(key);
    }

}
