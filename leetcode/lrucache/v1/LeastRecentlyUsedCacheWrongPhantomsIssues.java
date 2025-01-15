package v1;

import java.time.Instant;
import java.util.Objects;
import java.util.TreeMap;


record CacheKey(String name, Instant timestamp) implements Comparable<CacheKey> {

    public CacheKey(String name) {
        this(name, null);
    }

    @Override
    public int compareTo(CacheKey o) {
        if (timestamp.isBefore(o.timestamp)) return 1;
        if (timestamp.isAfter(o.timestamp)) return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(name, cacheKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
};

public class LeastRecentlyUsedCacheWrongPhantomsIssues <T> {

    private final TreeMap<CacheKey, T> cache = new TreeMap<>();
    private final int cacheSize;

    public LeastRecentlyUsedCacheWrongPhantomsIssues(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void put(String key, T value) {
        CacheKey cacheKey = new CacheKey(key);
        if (cache.containsKey(cacheKey)) {
            cache.remove(cacheKey);
        } else if (cache.size() >= cacheSize) {
            cache.remove(cache.lastEntry().getKey());
        }
        cache.put(new CacheKey(key, Instant.now()), value);
    }

    public T get(String key) {
        CacheKey cacheKey = new CacheKey(key);
        if (cache.containsKey(cacheKey)) {
            T value = cache.get(cacheKey);
            cache.remove(cacheKey);
            cache.put(new CacheKey(key, Instant.now()), value);
            return value;
        } else {
            return null;
        }
    }

    public void remove(String key) {
        cache.remove(new CacheKey(key));
    }

    public int size() {
        return cache.size();
    }

    public String getRecentlyUsed() {
        return cache.firstEntry().getKey().name();
    }

    public static void main(String[] args) {
        LeastRecentlyUsedCacheWrongPhantomsIssues<String > cache = new LeastRecentlyUsedCacheWrongPhantomsIssues<>(2);

        cache.put("first", "firstValue");
        System.out.println(cache.size() + " " + cache.get("first"));
        cache.put("second", "secondValue");
        System.out.println(cache.size() + " " + cache.get("second"));
        cache.put("third", "thirdValue");
        System.out.println(cache.size() + " " + cache.get("third"));

        cache.get("second");
        System.out.println(cache.getRecentlyUsed());

    }
}
