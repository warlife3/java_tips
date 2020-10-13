package com.ronzhin.tips.webserver.cache;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CacheImpl<K, V> implements Cache<K, V> {

    private final Map<K, ReferenceSoftHolder<K, V>> cacheStorage;
    private final Set<CacheListener<K, V>> listeners;
    private final ReferenceQueue<V> referenceQueue;
    private final Thread cacheCleaner;
    private boolean isWorking = true;

    public CacheImpl() {
        this.listeners = new LinkedHashSet<>();
        this.cacheStorage = new HashMap<>();
        this.referenceQueue = new ReferenceQueue<>();
        this.cacheCleaner = new Thread(this::run);
        this.cacheCleaner.start();
    }

    public long size() {
        return cacheStorage.size();
    }

    @Override
    public void put(K key, V value) {
        cacheStorage.put(key, new ReferenceSoftHolder<>(key, value, referenceQueue));
        listeners.forEach(cacheListener -> cacheListener.notify(key, value, "added"));
    }

    @Override
    public void close() {
        cacheCleaner.interrupt();
        isWorking = false;
    }

    @Override
    public void remove(K key) {
        V removedValue = cacheStorage.remove(key).get();
        listeners.forEach(cacheListener -> cacheListener.notify(key, removedValue, "removed"));
    }

    @Override
    public V get(K key) {
        var reference = cacheStorage.get(key);
        boolean isValueExist = reference != null && reference.get() != null;
        V value = isValueExist ? reference.get() : null;
        String message = isValueExist ? "Got existing value" : "try to get non existing value";
        listeners.forEach(cacheListener -> cacheListener.notify(key, value, message));
        return value;
    }

    @Override
    public void addListener(CacheListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(CacheListener<K, V> listener) {
        listeners.remove(listener);
    }

    private void run() {
        log.info("Cache cleaner start working");
        while (isWorking) {
            try {
                var value = (ReferenceSoftHolder<K, V>) referenceQueue.remove();
                cacheStorage.remove(value.getKey());
//                log.info("Removed key from map");
            } catch (InterruptedException e) {
//                log.error(e.getMessage(), e);
            }
        }
        log.info("Cache cleaner stop working");
    }

    private static class ReferenceSoftHolder<K, V> extends SoftReference<V> {
        @Getter
        private final K key;

        public ReferenceSoftHolder(K key, V value, ReferenceQueue<V> q) {
            super(value, q);
            this.key = key;
        }
    }
}