package com.ronzhin.tips.cache;


import com.ronzhin.tips.cache.core.model.BigObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * VM options: -Xmx256m -Xms256m
 */
public class ReferenceDemo {
    private static final Logger logger = LoggerFactory.getLogger(ReferenceDemo.class);

    public static void main(String[] args) throws InterruptedException {
//         strong();
//          weak();
          soft();
        //finalizeDemo();
        //phantom();
        //phantomDemo();
    }

    private static void strong() {
        int size = 1010;
        List<BigObject> references = new ArrayList<>(size);

        for (int k = 0; k < size; k++) {
            references.add(new BigObject());
            logger.info("k:{}", k);
        }
        //OutOfMemoryError for -Xmx256m -Xms256m
        logger.info("Size: {}", references.size());
    }

    private static void weak() {
        int size = 1010;
        List<WeakReference<BigObject>> references = new ArrayList<>(size);

        for (int k = 0; k < size; k++) {
            references.add(new WeakReference<>(new BigObject()));
        }

        //Если раскоментировать, то gc удалит все объекты
        // System.gc();

        int sum = 0;
        for (int k = 0; k < size; k++) {
            if (references.get(k).get() != null) {
                sum++;
            }
        }

        logger.info("Weak references: {}", sum);
    }

    private static void soft() {
        int size = 1010;
        List<SoftReference<BigObject>> references = new ArrayList<>(size);

        for (int k = 0; k < size; k++) {
            references.add(new SoftReference<>(new BigObject()));
        }

        // System.gc();

        int sum = 0;
        for (int k = 0; k < size; k++) {
            if (references.get(k).get() != null) {
                sum++;
            }
        }

        logger.info("Soft references: {}", sum);
    }

    private static void finalizeDemo() throws InterruptedException {
        var immortal = new Immortal(null);
        logger.info("immortal:{}", immortal);

        immortal = null;
        System.gc();
        Thread.sleep(TimeUnit.SECONDS.toMillis(3));

        logger.info("immortal:{}", immortal);
        ////

        Immortal[] backStore = {new Immortal(null)};
        Consumer<Immortal> revival = (obj) -> {
            backStore[0] = obj;
        };

        var immortalReal = new Immortal(revival);
        logger.info("immortal:{}", immortalReal);

        immortalReal = null;
        System.gc();
        Thread.sleep(TimeUnit.SECONDS.toMillis(3));

        immortalReal = backStore[0];
        logger.info("immortal:{}, backStore:{}", immortalReal, backStore[0]);
    }

    private static void phantom() throws InterruptedException {
        var a = new BigObject();
        logger.info("a: {}", a);

        //создаем очередь ReferenceQueue
        ReferenceQueue<BigObject> refQueue = new ReferenceQueue<>();

        //создаем Phantom Reference на объект типа BigObject и "подвязываем" ее на переменную a.
        PhantomReference<BigObject> phantomA = new PhantomReference<>(a, refQueue);
        logger.info("Ref in pool before GC: {}", refQueue.poll());

        logger.info("phantomA.get: {}", phantomA.get()); //всегда null

        a = null;
        //теперь объект "a" может быть удален сборщиком мусора.
        //До того, как gc сработает в refQueue будет null.


        System.gc();
        Thread.sleep(100);
        Reference<? extends BigObject> aa = refQueue.poll();
        logger.info("Ref in pool after GC: {}", aa);

        //Однако получить объект назад не получится
        BigObject resObject = aa.get();
        logger.info("resObject: {}", resObject);
    }

    private static void phantomDemo() throws InterruptedException {
        var a = new BigObject();
        ReferenceQueue<BigObject> refQueue = new ReferenceQueue<>();
        new PhantomReference<>(a, refQueue);

        new Thread(
                () -> {
                    try {
                        logger.info("Waiting for object cleaning...");
                        refQueue.remove();
                        logger.info("Object cleaned");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        ).start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        a = null;
        System.gc();
        Thread.sleep(100);
        logger.info("done, a:{}", a);
    }

    static class Immortal {
        Consumer<Immortal> revival;

        public Immortal(Consumer<Immortal> revival) {
            this.revival = revival;
        }

        @Override
        protected void finalize() {
            logger.info("finalize it");
            if (revival != null) {
                revival.accept(this);
            }
        }
    }
}
