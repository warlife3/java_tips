package com.ronzhin.tips.cache;

import com.ronzhin.tips.cache.core.model.BigObject;
import com.ronzhin.tips.cache.custom.Cache;
import com.ronzhin.tips.cache.custom.CacheImpl;
import com.ronzhin.tips.cache.custom.CacheListener;
import com.ronzhin.tips.cache.core.service.DataSorce;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * VM options: -Xmx256m -Xms256m
 */
@Slf4j
public class CacheDemoOOM {
    private static final int OBJECT_COUNT = 1000;
    private static Cache<Integer, BigObject> cache;

    public static void main(String[] args) {
        withoutCache();
//        withCache();
    }

    private static void withoutCache() {
        List<BigObject> objects = new ArrayList<>();

        log.info("Start creating");
        for (int i = 0; i < OBJECT_COUNT; i++) {
            log.info("Created object by number: {}", i + 1);
            objects.add(DataSorce.getValue(i));
        }
        log.info("Start getting");
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i);
        }
    }

    private static void withCache() {
        initEhcache();

        log.info("Start creating");
        for (int i = 0; i < OBJECT_COUNT; i++) {
            log.info("Created object by number: {}", i + 1);
            getValue(i);
        }

        closeEhcache();

        log.info("Cache size suppose to be {} but in real he is {}", OBJECT_COUNT, cache.size());
    }

    private static BigObject getValue(int key) {
        BigObject value = cache.get(key);
        if (value == null) {
            value = DataSorce.getValue(key);
            cache.put(key, value);
        }
        return value;
    }

    private static void initEhcache() {
        cache = new CacheImpl<>();

        CacheListener<Integer, BigObject> listener = new CacheListener<>() {
            @Override
            public void notify(Integer key, BigObject value, String action) {
//                log.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };

        cache.addListener(listener);
        log.info("Cache setup is done");
    }

    @SneakyThrows
    private static void closeEhcache() {
        cache.close();
    }

}