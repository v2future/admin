package com.hollycrm.mi.core.cache.redis;

import com.hollycrm.mi.core.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisCacheManager implements CacheManager {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void put(String key, Object objValue) {
        String value = null;
        if ( objValue instanceof  String) {
            value = (String)objValue;
        }
        //TODO
        redisTemplate.opsForValue().set(key, value, Duration.ofDays( 365 * 100));
    }

    @Override
    public void put(String key, Object objValue, int timeout) {
        String value = null;
        if ( objValue instanceof  String) {
            value = (String)objValue;
        }
        //TODO
        redisTemplate.opsForValue().set(key, value, Duration.ofMillis( timeout));
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        String value = null;
        if ( String.class.equals( type)) {
            return (T)redisTemplate.opsForValue().get(key);
        }
        //TODO
        return null;
    }

    @Override
    public void clear(String key) {
        redisTemplate.opsForValue().set(key, null, Duration.ZERO);
    }
}
