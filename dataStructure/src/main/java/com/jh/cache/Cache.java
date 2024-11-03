package com.jh.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An interview question which ask to implement a cache.
 * Requirements:
 * 1. supporting the expiry time
 * 2. MultiThread safe.
 * 3. Singleton
 * 4. Support any date type
 */
public class Cache<K, V> {
    private static Cache cache;
    private final Map<K, CacheElement<V>> concurrentHashMap = new ConcurrentHashMap();
    private final ScheduledExecutorService cleaner = Executors.newScheduledThreadPool(1);

    private Cache() {
        // Schedule a cleanup task to remove expired elements
        cleaner.scheduleAtFixedRate(this::cleanup, 1, 1, TimeUnit.SECONDS);
    }

    public static <K, V> Cache<K, V> getCache() {
        if (cache == null) {
            synchronized (Cache.class) {
                if (cache == null) {
                    cache = new Cache<>();
                }
            }
        }
        return (Cache<K, V>) cache;
    }

    public void set(K key, V Value, long timeoutInMills) {
        concurrentHashMap.put(key, new CacheElement<V>(Value, System.currentTimeMillis() + timeoutInMills));
    }

    public V get(K key) {
        CacheElement<V> cacheElement = concurrentHashMap.get(key);
        if (cacheElement == null) {
            return null;
        }
        if (cacheElement.isExpired()) {
            concurrentHashMap.remove(key);
            return null;
        }
        return cacheElement.value;
    }

    /**
     * Clean up expired elements
     */
    private void cleanup() {
        concurrentHashMap.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public static class CacheElement<V> {
        private V value;
        private long expiryTime;

        public CacheElement(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > Long.valueOf(expiryTime);
        }
    }

    public static void main(String[] args) {
        Cache cache = Cache.getCache();
        cache.set("Iphone", "Iphone15", 1000L);
        cache.set("Macbook", "MacbookPro", 2000L);
        System.out.println(cache.get("Iphone"));
        try {

            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(cache.get("Iphone"));
        System.out.println(cache.get("Macbook"));
    }

}
