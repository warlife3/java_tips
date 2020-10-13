package com.ronzhin.tips.cache;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This demo is illustrating the problem lambda and WeekReference problem in Java
 * VM options:
 * -Xmx5m -Xms5m -> allow us to call System.gc(); and GC will do it. Because we do not have enough heap space.
 * -verbose:gc -> Allow us to see what GC does
 */
@Slf4j
public class LambdaWeekReferenceDemo {
    static List<WeakReference<Consumer<String>>> stringWeekConsumers = new ArrayList<>();

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Consumer<String> stringConsumer = null;
            int createConsumer = 2; // change me. Possible cases are [1,2,3]
            switch (createConsumer) {
                case 1:
                    stringConsumer = s -> System.out.println(s);
                    break;
                case 2:
                    stringConsumer = System.out::println;
                    break;
                case 3:
                    stringConsumer = new Consumer<>() {
                        @Override
                        public void accept(String s) {
                            System.out.println(s);
                        }
                    };
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            log.info("Class of consumer: {}", stringConsumer.getClass());
            stringWeekConsumers.add(new WeakReference<>(stringConsumer));
        }
        System.out.println("Just chill");
        Runtime.getRuntime().gc();

        var survivedLinks = stringWeekConsumers.stream().map(Reference::get).filter(Objects::nonNull).count();
        System.out.println("survivedLinks " + survivedLinks);

        var anyStringConsumer = stringWeekConsumers.stream().map(Reference::get).filter(Objects::nonNull).findAny();
        anyStringConsumer.ifPresentOrElse(consumer -> consumer.accept("Hello World from survived consumer!"), () -> System.out.println("NO consumers"));
    }
}