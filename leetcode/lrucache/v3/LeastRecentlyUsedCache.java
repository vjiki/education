package v3;

import java.util.LinkedHashMap;


public class LeastRecentlyUsedCache<K, V> {
    private final LinkedHashMap<K, V> cache;
    private final int capacity;

    public LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else {
            if (cache.size() >= capacity) {
                cache.remove(cache.firstEntry().getKey());
            }
        }
        cache.put(key, value);
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            V value = cache.get(key);
            cache.remove(key);
            cache.put(key, value);
            return value;
        } else {
            return null;
        }

    }

    public int size() {
       return cache.size();
    }

    public K getRecentlyUsed() {
        return cache.lastEntry().getKey();
    }

    public K getLeastRecentlyUsed() {
        return cache.firstEntry().getKey();
    }

    public static void main(String[] args) {
        LeastRecentlyUsedCache<String, String> cache = new LeastRecentlyUsedCache<>(2);
        cache.put("first", "firstValue");
        System.out.println(cache.size() + " " + cache.get("first"));
        cache.put("second", "secondValue");
        System.out.println(cache.size() + " " + cache.get("second"));
        cache.put("third", "thirdValue");
        System.out.println(cache.size() + " " + cache.get("third"));

        cache.get("second");
        System.out.println(cache.getRecentlyUsed());
        cache.get("third");
        System.out.println(cache.getRecentlyUsed());
        cache.get("first");
        System.out.println(cache.getRecentlyUsed());
        System.out.println(cache.size());
        cache.get("second");
        System.out.println(cache.getLeastRecentlyUsed());


    }
}
