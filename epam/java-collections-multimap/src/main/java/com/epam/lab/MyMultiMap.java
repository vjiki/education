package com.epam.lab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private final HashMap<K, List<V>> map;
    private final int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    /**
     * returns the size of our map
     */
    @Override
    public int size() {
        int size = 0;
        if(!map.isEmpty()) {
            for (K key: map.keySet()) {
                size += map.get(key).size();
            }
        }
        return size;
    }

    /**
     * should add a value by the key
     * If map already contains the key, and the count of values by this key is less than repeatCount,
     * then you should add the value to the end of list.
     * If the count of values by this key is equal to repeatCount - then you should remove the first
     * element from the list (with 0 index) and add the value to the end of list.
     * Method should return the last added value to the list
     * (but not the element which you've added right now)
     * If there are no elements yet by this key - then return null.
     */
    @Override
    public V put(K key, V value) {
        List<V> currentValues;
        V returnValue = null;

        currentValues = map.putIfAbsent(key, Collections.singletonList(value));
        if (currentValues != null && currentValues.size() <= repeatCount) {
            returnValue = currentValues.get(currentValues.size()-1);
            if (currentValues.size() == repeatCount) {
                currentValues.remove(0);
            }
            ArrayList<V> arrayList = new ArrayList<>(currentValues);
            arrayList.add(value);
            map.put(key, arrayList);
        }

        return returnValue;
    }

    /**
     * should remove an element by the key.
     * If there are multiple elements by this key, you should remove the first element from list (with index 0)
     * If there are no elements (empty list) by this key - then you should completely remove this key-value pair.
     * Method should return removed element. If there is no such key - then return null
     */
    @Override
    public V remove(Object key) {
        if (map.containsKey(key)) {
            List<V> currentValues = map.get(key);
            //System.out.println("removing " + currentValues.size());
            if (currentValues.size() > 1 && currentValues.get(0) != null) {
                //System.out.println("removing 1");
                V removedValue = currentValues.remove(0);
                map.remove(key);
                map.put((K) key, currentValues);
                return removedValue;
            }
            map.remove(key, currentValues);
            return currentValues.get(0);
        }
        return null;
    }

    /**
     * should return a set of all keys, that are contained in map
     */
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * should return ArrayList<V> of all values. The order of elements in list doesn't matter
     */
    @Override
    public Collection<V> values() {
        ArrayList<V> arrayList = new ArrayList<>();
        if(!map.isEmpty()) {
            for (K key: map.keySet()) {
                List<V> values = map.get(key);
                arrayList.addAll(values);
            }
        }
        return arrayList;
    }

    /**
     * returns true if map contains the key, otherwise returns false
     */
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * returns true if map contains the value, otherwise returns false
     */
    @Override
    public boolean containsValue(Object value) {

        boolean contains = false;

        if(!map.isEmpty()) {
            for (K key: map.keySet()) {
                contains |= map.get(key).contains(value);
            }
        }
        return contains;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}