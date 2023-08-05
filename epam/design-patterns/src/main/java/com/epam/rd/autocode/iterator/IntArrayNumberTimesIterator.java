package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayNumberTimesIterator implements Iterator<Integer> {
    int[] array;
    int numberOfTimes = 1;
    int position = 0;

    public IntArrayNumberTimesIterator(int[] array, int numberOfTimes) {
        this.array = array;
        if (numberOfTimes >= 1) {
            this.numberOfTimes = numberOfTimes;
        }
    }

    @Override
    public Integer next() {
        if (position/numberOfTimes >= array.length) {
            throw new NoSuchElementException();
        }
        int value = array[position/numberOfTimes];
        position = position + 1;
        return value;
    }

    @Override
    public boolean hasNext() {
        return position / numberOfTimes < array.length;
    }
}
