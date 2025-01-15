package v2;

import java.util.HashMap;
import java.util.LinkedList;


public class LeastRecentlyUsedCache<T> {
    private final HashMap<String, T> cache = new HashMap<>();
    private final LinkedList<String> list = new LinkedList<>();
    private final int capacity;

    public LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(String key, T value) {
        if (cache.containsKey(key)) {
            list.remove(key);
            list.addFirst(key);
        } else if (list.size() >= capacity) {
            String last = list.getLast();
            cache.remove(last);
            list.removeLast();
        }
        cache.put(key, value);
        list.addFirst(key);
    }

    public T get(String key) {
        if (!list.isEmpty() && cache.containsKey(key) && !list.getFirst().equals(key)) {
            list.remove(key);
            list.addFirst(key);
        }
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
        list.remove(key);
    }

    public int size() {
        if (list.size() != cache.size()) {
            throw new RuntimeException("list size mismatch");
        }
        return list.size();
    }

    public String getRecentlyUsed() {
        return list.getFirst();
    }

    public static void main(String[] args) {
        LeastRecentlyUsedCache<String> cache = new LeastRecentlyUsedCache<>(2);
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


    }
}
