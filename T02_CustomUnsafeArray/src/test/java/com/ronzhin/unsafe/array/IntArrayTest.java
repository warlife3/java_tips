package com.ronzhin.unsafe.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

public class IntArrayTest {

    private IntArray myArr;

    @Before
    public void setUp() throws Exception {
        int arraySizeInit = 10;
        myArr = new IntArray(arraySizeInit);
    }

    @After
    public void clean() {
        myArr.close();
    }

    @Test
    public void storeCurrentValues() {
        int arraySizeMax = 20;

        for (int i = 0; i < arraySizeMax; i++) {
            myArr.addValue(i);
        }

        int[] array = IntStream.range(0, arraySizeMax).toArray();

        assertThat(array.length).isEqualTo(myArr.getSize());

        for (int i = 0; i < array.length; i++) {
            int expectedResult = array[i];
            int result = myArr.get(i);
            assertThat(result).isEqualTo(expectedResult);
        }
    }

    @Test
    public void storeCurrentNumberOfValues() {
        int arraySizeMax = 20;
        int expectedResult = arraySizeMax;

        for (int i = 0; i < arraySizeMax; i++) {
            myArr.addValue(i);
        }

        int result = myArr.getSize();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void storeCurrentValuesCheckBySum() {
        int arraySizeMax = 1_000_000;
        long result = 0;

        for (int i = 0; i < arraySizeMax; i++) {
            myArr.addValue(i);
        }

        for (int i = 0; i < myArr.getSize(); i++) {
            result += myArr.get(i);
        }

        long expectedResult = LongStream.range(0, arraySizeMax).sum();
        assertThat(result).isEqualTo(expectedResult);

    }

}