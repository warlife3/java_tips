package com.ronzhin.tips.cache.custom;

import com.sun.management.GarbageCollectionNotificationInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * -XX:+UseSerialGC
 * -XX:+UseParallelGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseG1GC
 */

@Slf4j
@DisplayName("Test Cache ")
class CacheImplTest {
    private final int TEST_CASES = 10;
    private Cache<Integer, Integer> cache;

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

//                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                    System.out.printf("Start: %d, name: %s, action: %s, gcCause%s (%d ms) \n", startTime, gcName, gcAction, gcCause, duration);
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }

    @BeforeEach
    public void setUp() {
        cache = new CacheImpl<>();
        for (int i = 0; i < TEST_CASES; i++) {
            cache.put(i, i);
        }
    }

    @Test
    @DisplayName(" should have correct size")
    public void shouldHaveCorrectSize() {
        assertThat(cache.size()).isEqualTo(TEST_CASES);
    }

    @Test
    @DisplayName(" should have all correct data ")
    public void shouldGetAllData() {
        List<Integer> values = new ArrayList<>();
        List<Integer> expectedValues = IntStream.range(0, TEST_CASES).collect(ArrayList::new, List::add, List::addAll);
        IntStream.range(0, TEST_CASES).forEach(i -> values.add(cache.get(i)));
        assertThat(values).containsExactlyElementsOf(expectedValues);
    }

    /**
     * -Xmx50m -Xms50m -XX:+UseParallelGC -Xlog:gc+heap
     * -Xmx50m -Xms50m -XX:+UseG1GC -verbose:gc -XX:MaxGCPauseMillis=2 - 2.227s
     * -Xmx50m -Xms50m -XX:+UseG1GC -verbose:gc -XX:MaxGCPauseMillis=1 -XX:+HeapDumpOnOutOfMemoryError -Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
     */
    @Test
    @DisplayName(" failed when cache is filling mach faster")
    public void test() {
//        switchOnMonitoring();
        for (int i = 0; i < 500_000; i++) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            cache.put(i, i);
        }
        log.info("test done. cache size: {}", cache.size());
    }

    @AfterEach
    public void clear() {
        try {
            cache.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}