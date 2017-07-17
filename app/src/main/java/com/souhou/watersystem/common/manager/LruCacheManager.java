package com.souhou.watersystem.common.manager;

import android.util.LruCache;

/**
 * Created by jayli on 2017/5/3 0003.
 */

public class LruCacheManager {
    /**
     * lruCache
     */
    private LruCache<String, Object> lruCache;
    /**
     * instance
     */
    private static LruCacheManager instance;
    /**
     * cache size
     */
    private final int CACHE_SIZE = 50;


    /**
     *  LruCacheManager constructor
     */
    private LruCacheManager() {
        this.lruCache = new LruCache<String, Object>(CACHE_SIZE);
    }

    /**
     * get the LruCacheManager instance, it is the singleton
     *
     * @return LruCacheManager
     */
    public static LruCacheManager getInstance() {
        if (instance == null) {
            synchronized (LruCacheManager.class) {
                if (instance == null) {
                    instance = new LruCacheManager();
                }
            }
        }
        return instance;
    }

    /**
     * put
     * @param key String
     * @param value Object
     */
    public void put(String key, Object value) {
        lruCache.put(key, value);
    }

    /**
     * get
     * @param key String
     * @return Object
     */
    public Object get(String key) {
        return lruCache.get(key);
    }

    /**
     * remove
     * @param key String
     * @return Object
     */
    public Object remove(String key) {
        return lruCache.remove(key);
    }

    /**
     * evictAll
     */
    public void evictAll() {
        lruCache.evictAll();
    }

    /**
     * maxSize
     * @return int
     */
    public int maxSize() {
        return lruCache.maxSize();
    }

    /**
     * size
     * @return int
     */
    public int size() {
        return lruCache.size();
    }

    /**
     * trimToSize
     * @param maxSize
     */
    public void trimToSize(int maxSize) {
        lruCache.trimToSize(maxSize);
    }
}
