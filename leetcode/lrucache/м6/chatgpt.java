import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

public class LRUCache<K, V> {
    private final ConcurrentSkipListMap<Long, K> orderMap; // Для отслеживания порядка
    private final ConcurrentSkipListMap<K, V> cacheMap;    // Хранение данных
    private final int capacity;
    private final AtomicLong counter; // Счетчик для отслеживания времени использования

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.orderMap = new ConcurrentSkipListMap<>();
        this.cacheMap = new ConcurrentSkipListMap<>();
        this.counter = new AtomicLong();
    }

    // Получение элемента из кэша
    public V get(K key) {
        if (!cacheMap.containsKey(key)) {
            return null;
        }

        // Обновляем порядок использования
        Long oldTimestamp = orderMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(key))
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);

        if (oldTimestamp != null) {
            orderMap.remove(oldTimestamp);
        }

        long newTimestamp = counter.incrementAndGet();
        orderMap.put(newTimestamp, key);

        return cacheMap.get(key);
    }

    // Добавление элемента в кэш
    public void put(K key, V value) {
        // Удаление старого ключа, если он существует
        get(key);

        // Если кэш заполнен, удаляем LRU-элемент
        if (cacheMap.size() >= capacity) {
            Long oldestTimestamp = orderMap.firstKey();
            K oldestKey = orderMap.remove(oldestTimestamp);
            cacheMap.remove(oldestKey);
        }

        // Добавляем новый элемент
        long timestamp = counter.incrementAndGet();
        orderMap.put(timestamp, key);
        cacheMap.put(key, value);
    }
}
