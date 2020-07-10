package com.hollycrm.mi.core.cache;

/**
 * 支持字符串格式的k-v缓存
 * 建议把普通对象转成json后再提交缓存
 */
public interface CacheManager {

    void put(String key, Object value);

    /**
     *
     * @param key
     * @param value
     * @param timeout 单位：分钟
     */
    void put(String key, Object value, int timeout);

    <T>T get(String key, Class<T> type);

    void clear(String key);
}
