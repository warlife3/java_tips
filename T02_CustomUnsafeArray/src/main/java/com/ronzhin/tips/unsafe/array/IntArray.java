package com.ronzhin.tips.unsafe.array;

import sun.misc.Unsafe;

import java.io.Closeable;
import java.lang.reflect.Constructor;

public class IntArray implements Closeable {

    private final Unsafe unsafe;
    private final long elementSizeBytes;

    private int capacity;
    private int size;
    private long arrayPointer;

    public IntArray(int capacity) throws Exception {
        this.capacity = capacity;
        this.size = 0;
        this.elementSizeBytes = Integer.SIZE / 8;

        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        this.unsafe = unsafeConstructor.newInstance();

        allocate(capacity);
    }

    public void add(int value) {
        if (size >= capacity) {
            capacity <<= 1;
            reallocate(capacity);
        }
        long memoryPosition = index(size);
        unsafe.putInt(memoryPosition, value);
        size++;
    }

    public int get(long index) {
        long memoryPosition = index(index);
        return unsafe.getInt(memoryPosition);
    }

    public int getSize() {
        return size;
    }

    @Override
    public void close() {
        unsafe.freeMemory(arrayPointer);
    }

    private long index(long offset) {
        return arrayPointer + offset * this.elementSizeBytes;
    }

    private void allocate(int capacity) {
        arrayPointer = unsafe.allocateMemory(capacity * elementSizeBytes);
    }

    private void reallocate(int capacity) {
        arrayPointer = unsafe.reallocateMemory(arrayPointer, capacity * elementSizeBytes);
    }

}