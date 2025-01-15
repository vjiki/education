import java.util.LinkedHashMap;
import java.util.Map;

public class SynchronizedLRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public SynchronizedLRUCache(int capacity) {
        super(capacity, 0.75f, true); // true для порядка доступа
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public K getRecentlyUsed() {
        return this.lastEntry().getKey();
    }

    public K getLeastRecentlyUsed() {
        return this.firstEntry().getKey();
    }


    public static void main(String[] args) {
        SynchronizedLRUCache<String, String> cache = new SynchronizedLRUCache<>(2);
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

