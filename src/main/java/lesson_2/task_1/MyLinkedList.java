package lesson_2.task_1;

import lesson_2.MyList;

public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private Object[] data;
    transient Node<E> first;
    transient Node<E> last;

    public MyLinkedList() {
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
        linkLast(value);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> current = first; current != null; current = current.next) {
                if (current.item == null) {
                    unlink(current);
                    return true;
                }
            }
        } else {
            for (Node<E> current = first; current != null; current = current.next) {
                if (o.equals(current.item)) {
                    unlink(current);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    void linkLast(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    E unlink(Node<E> current) {
        final E element = current.item;
        final Node<E> next = current.next;
        final Node<E> prev = current.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            current.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            current.next = null;
        }

        current.item = null;
        size--;
        return element;
    }
}
