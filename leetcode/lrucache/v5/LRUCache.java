package v5;

import java.util.*;

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node> cache;
    private final DoublyLinkedList list;

    // Node class to represent each entry in the doubly linked list
    private class Node {
        K key;
        V value;
        Node prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Doubly linked list to store nodes in the order of use (most recent first)
    private class DoublyLinkedList {
        Node head, tail;

        DoublyLinkedList() {
            this.head = new Node(null, null);  // dummy head
            this.tail = new Node(null, null);  // dummy tail
            head.next = tail;
            tail.prev = head;
        }

        // Add a node to the front of the list
        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // Remove a node from the list
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Remove the last node (least recently used)
        Node removeLast() {
            if (head.next == tail) {
                return null; // No elements in the list
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList();
    }

    // Get the value for the given key
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null; // Key not found
        }
        // Move the accessed node to the front (most recently used)
        list.remove(node);
        list.addFirst(node);
        return node.value;
    }

    // Put a key-value pair into the cache
    public void put(K key, V value) {
        Node node = cache.get(key);

        if (node != null) {
            // Update the value if the key already exists
            node.value = value;
            // Move the node to the front (most recently used)
            list.remove(node);
            list.addFirst(node);
        } else {
            // If the key doesn't exist, add it to the cache
            if (cache.size() >= capacity) {
                // Cache is full, remove the least recently used item
                Node last = list.removeLast();
                if (last != null) {
                    cache.remove(last.key);
                }
            }
            // Create a new node and add it to the front
            Node newNode = new Node(key, value);
            list.addFirst(newNode);
            cache.put(key, newNode);
        }
    }

    // Display the current state of the cache for debugging (optional)
    public void displayCache() {
        Node current = list.head.next;
        while (current != list.tail) {
            System.out.print(current.key + ":" + current.value + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Example usage
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        // Add items to the cache
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");

        // Display the cache
        lruCache.displayCache(); // Expected: 3:C -> 2:B -> 1:A -> null

        // Access some keys
        System.out.println("Get key 1: " + lruCache.get(1));  // Expected: A
        lruCache.displayCache(); // Expected: 1:A -> 3:C -> 2:B -> null

        // Add a new item, causing eviction of the least recently used item (key 2)
        lruCache.put(4, "D");
        lruCache.displayCache(); // Expected: 4:D -> 1:A -> 3:C -> null

        // Get some more items
        System.out.println("Get key 2: " + lruCache.get(2));  // Expected: null (evicted)
        System.out.println("Get key 3: " + lruCache.get(3));  // Expected: C
    }
}

