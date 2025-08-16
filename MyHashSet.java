import java.util.ArrayList;

import java.util.Arrays;

public class MyHashSet<T> {

    private final ArrayList<ArrayList<T>> buckets;

    private final int numBuckets = 16;

    public MyHashSet() {

        this.buckets = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; ++i)
            buckets.add(new ArrayList<>());

    }

    private int hash(T t) {

        if (t != null) {
            return Math.abs(t.hashCode()) % numBuckets;
        } else {
            return 0;
        }

    }

    public void insert(T el) {

        int index = hash(el);
        ArrayList<T> bucket = buckets.get(index);

        if (bucket.contains(el)) return;

        bucket.add(el);
    }

    public boolean remove(T el) {

        int index = hash(el);
        ArrayList<T> bucket = buckets.get(index);
        return bucket.remove(el);

    }

    @Override
    public String toString() {
        return Arrays.toString(buckets.toArray());
    }

}