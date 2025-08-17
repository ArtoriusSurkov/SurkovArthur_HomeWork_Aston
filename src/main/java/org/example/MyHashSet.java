package main.java.org.example;

import java.util.LinkedList;
import java.util.List;

public class MyHashSet<E> {

    private final List<List<E>> buckets;

    private final int numBuckets = 4;

    public MyHashSet() {

        this.buckets = new LinkedList<>();

        for (int i = 0; i < numBuckets; ++i)
            buckets.add(new LinkedList<>());

    }

    private int hash(E e) {

        if (e != null) {
            return Math.abs(e.hashCode()) % numBuckets;
        } else {
            return 0;
        }

    }

    public void add(E el) {

        int index = hash(el);
        List<E> bucket = buckets.get(index);

        if (bucket.contains(el)) return;

        bucket.add(el);
    }

    public boolean remove(E el) {

        int index = hash(el);
        List<E> bucket = buckets.get(index);
        return bucket.remove(el);

    }

    @Override
    public String toString() {
        return "MyHashSet{" +
                "buckets=" + buckets +
                '}';
    }
}