package com.ronzhin.tips.cache.custom;

public interface CacheListener<K, V> {
    void notify(K key, V value, String action);
}