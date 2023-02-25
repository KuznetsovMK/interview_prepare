package lesson_2.task_2;

import lesson_2.MyList;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    private int size;
    private Object[] data;
    private int defaultCapacity = 10;
    private int newCapacity;

    public MyArrayList() {
        this.size = 0;
        this.data = new Object[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E value) {
        add(value, data, size);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] data1 = data;
        final int size = this.size;

        for (int i = 0; i < size; i++) {
            if (data1[i] == null) {
                return remove(data1, i);
            } else if (data1[i].equals(o)) {
                return remove(data1, i);
            }
        }
        return false;
    }

    private boolean remove(Object[] data1, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(data1, i + 1, data1, i, newSize - i);
        data1[size = newSize] = null;
        return true;
    }

    private void add(E value, Object[] data, int size) {
        if (data.length == size) {
            if (data.length == 0) {
                this.data = new Object[defaultCapacity];
            } else {
                this.newCapacity = size * 2;
                this.data = Arrays.copyOf(data, newCapacity);
            }
        }
        this.data[size] = value;
        this.size += 1;
    }

    public Object[] toArray() {
        return Arrays.copyOfRange(data, 0, size);
    }
}
