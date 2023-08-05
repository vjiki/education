package com.epam.rd.autocode.iterator;

import java.util.Iterator;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new IntArrayNumberTimesIterator(array, 2);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new IntArrayNumberTimesIterator(array, 3);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new IntArrayNumberTimesIterator(array, 5);
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return new CellsIterabale(columns, rows);
    }

}
