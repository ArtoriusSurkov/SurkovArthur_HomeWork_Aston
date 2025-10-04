package org.homeWork;

import java.util.Arrays;
import java.util.Objects;

public class TestHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Node<K, V>[] table;
    private int size;
    private static final int INITIAL_CAPACITY = 4;

    public TestHashMap() {
        this.size = 0;
        this.table = new Node[INITIAL_CAPACITY];
    }

    public int hash(K key) {
        return (Objects.hashCode(key) % table.length + table.length) % table.length;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> current = table[index];

        if (current == null) {
            table[index] = newNode;
            size++;
        } else {
            Node<K, V> prev = null;

            while (current != null) {
                if (Objects.equals(current.key, key)) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode;
            size++;
        }
    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }
}
