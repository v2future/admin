package com.hollycrm.mi.core.kit;

import com.hollycrm.mi.core.cache.CacheManager;

/**
 * 缓存的简洁入口
 */
public class CacheKit {

    private static CacheManager cacheManager;

    public static void init(CacheManager cacheManager) {
        CacheKit.cacheManager = cacheManager;
    }

    public static void put(String key, Object value) {
        cacheManager.put(key, value);
    }

    /**
     *
     * @param key
     * @param value
     * @param timeout 单位：分钟
     */
    public static void put(String key, Object value, int timeout) {
        cacheManager.put(key, value, timeout);
    }

    public static <T> T get(String key, Class<T> type) {
        T t = cacheManager.get(key, type);
        return t;
    }
}
