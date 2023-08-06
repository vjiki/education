package com.epam.lab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> extends ArrayList<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return MyArrayList.super.size() != 0;
            }

            @Override
            public T next() {
                currentIndex = currentIndex < MyArrayList.super.size() ?  currentIndex : 0;
                return MyArrayList.super.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        int count = 0;
        for (Integer i : myList) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) break;
        }
    }
}
