package com.epam.lab;

import java.util.List;


public abstract class Solution {

    /**
     * should work with the same type T
     */
    public abstract <T> void one(List<T> target, List<T> source);

    /**
     * should add any subclasses of type T to the list that can only store elements of type T
     */
    public abstract <T> void two(List<T> target, List<? extends T> source);

    /**
     * should add elements of type T to the list that parametrized with parent class
     */
    public abstract <T> void three(List<? super T> target, List<T> source);

    /**
     * should add any subclasses of type T to the list that parametrized with parent class
     */
    public abstract <T> void four(List<? super T> target, List<? extends T> source);
}