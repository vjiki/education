package v4;

import java.util.HashMap;


public class LeastRecentlyUsedCache<K, V> {
    private final HashMap<K, Node<K, V>> cache;
    private final DoubleLinkedList<K,V> order = new DoubleLinkedList<>();
    private final int capacity;

    public LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity, 0.75f);
    }

    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);

        if (node != null) {
            // Update the value if the key already exists
            node.value = value;
            // Move the node to the front (most recently used)
            order.remove(node);
            order.addFirst(node);
        } else {
            // If the key doesn't exist, add it to the cache
            if (cache.size() >= capacity) {
                // Cache is full, remove the least recently used item
                Node<K, V> last = order.removeLast();
                if (last != null) {
                    cache.remove(last.key);
                }
            }
            // Create a new node and add it to the front
            Node<K, V> newNode = new Node<K, V>(key, value);
            order.addFirst(newNode);
            cache.put(key, newNode);
        }
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            Node<K,V> node = cache.get(key);
            order.remove(node);
            order.addFirst(node);
            return node.getValue();
        } else {
            return null;
        }

    }

    public int size() {
       return cache.size();
    }

    public K getRecentlyUsed() {
        return order.getHead().next.key;
    }

    public K getLeastRecentlyUsed() {
        return order.getTail().prev.key;
    }

    public static class DoubleLinkedList<K,V> {
        Node<K,V> head;
        Node<K,V> tail;
        int size;
        public DoubleLinkedList() {
            this.head = new Node<>(null, null);  // dummy head
            this.tail = new Node<>(null, null);  // dummy tail
            head.next = tail;
            tail.prev = head;
        }

        Node<K, V> getHead() {
            return head;
        }

        Node<K, V> getTail() {
            return tail;
        }

        void remove(Node<K,V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void addFirst(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // Remove the last node (least recently used)
        Node<K, V> removeLast() {
            if (head.next == tail) {
                return null; // No elements in the list
            }
            Node<K, V> last = tail.prev;
            remove(last);
            return last;
        }
    }

    public static class Node <K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        private Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
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
