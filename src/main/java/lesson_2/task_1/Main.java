package lesson_2.task_1;

import lesson_2.MyList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /**
         * 1. Реализовать основные методы связанного списка.
         * */

        MyList<String> values1 = new MyLinkedList<>();
        System.out.println(values1.size());
        System.out.println(values1.isEmpty());

        values1.add("value1");
        values1.add("value2");
        values1.add("value3");
        values1.add("value4");
        values1.add("value5");
        System.out.println(values1.size());
        System.out.println(values1.isEmpty());
        System.out.println(Arrays.stream(values1.toArray()).toList());

        values1.remove("value2");
        System.out.println(Arrays.stream(values1.toArray()).toList());

        values1.remove(null);
        System.out.println(Arrays.stream(values1.toArray()).toList());

        values1.remove("2");
        System.out.println(Arrays.stream(values1.toArray()).toList());
    }
}
