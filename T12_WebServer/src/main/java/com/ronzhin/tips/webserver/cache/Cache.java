package com.ronzhin.tips.webserver.cache;

public interface Cache<K, V> extends AutoCloseable {

    void put(K key, V value);

    void remove(K key);

    V get(K key);

    void addListener(CacheListener<K, V> listener);

    void removeListener(CacheListener<K, V> listener);

    long size();

}