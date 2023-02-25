package lesson_2;

public interface MyList<E> {
    int size();

    boolean isEmpty();

    boolean add(E e);

    boolean remove(Object o);

    Object[] toArray();
}
