import java.util.Arrays;

public class MyArrayList<T> {

    private final int defCapas = 8;

    private int size;

    private T[] elements;

    public MyArrayList() {

        this.elements = (T[]) new Object[defCapas];
        this.size = 0;

    }

    public void add(T element) {

        if (size >= elements.length) {
            resize();
        }
        elements[size++] = element;

    }

    public T get(int index) {

        checkIndex(index);
        return elements[index];

    }

    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
    }

    public void addAll(T[] collection) {

        int collectionLength = collection.length;

        validCapacity(size + collectionLength);
        System.arraycopy(collection, 0, elements, size, collectionLength);
        size += collectionLength;

    }

    private void resize() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне границ массива!");
        }
    }

    private void validCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            resize();
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);
            if (i < size - 1) stringBuilder.append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
