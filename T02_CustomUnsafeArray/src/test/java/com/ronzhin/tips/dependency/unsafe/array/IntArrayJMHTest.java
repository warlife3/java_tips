package com.ronzhin.tips.dependency.unsafe.array;

import com.ronzhin.tips.unsafe.array.IntArray;
import org.junit.jupiter.api.AfterEach;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup
public class IntArrayJMHTest {

    private static final int ARRAY_SIZE_MAX = 100_000_000;
    private static final int ARRAY_SIZE_INIT = 10;
    private IntArray myArr;
    private List<Integer> arrayList;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(IntArrayJMHTest.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() throws Exception {
        myArr = new IntArray(ARRAY_SIZE_INIT);
        arrayList = new ArrayList<>(ARRAY_SIZE_INIT);
    }

    @AfterEach
    public void clean() {
        myArr.close();
    }

    @Benchmark
    public long myArrayIntTest() {
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            myArr.add(i);
        }

        long summ = 0;
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            summ += myArr.get(i);
        }
        return summ;
    }

    @Benchmark
    public long ArrayListTest() {
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            arrayList.add(i, i);
        }

        long summ = 0;
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            summ += arrayList.get(i);
        }
        return summ;
    }

}