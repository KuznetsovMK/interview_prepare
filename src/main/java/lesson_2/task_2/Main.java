package lesson_2.task_2;

import lesson_2.MyList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        /**
         * 2. Реализовать основные методы ArrayList.
         * */

        MyList<String> values = new MyArrayList<>();
        System.out.println(values.size());
        System.out.println(values.isEmpty());

        values.add("value1");
        values.add("value2");
        values.add("value3");
        values.add("value4");
        values.add("value5");
        values.add("value6");
        values.add("value7");
        values.add("value8");
        values.add("value9");
        values.add("value10");
        values.add("value11");
        values.add("value12");
        values.add("value13");
        values.add("value14");
        values.add("value15");
        values.add(null);
        values.add("value16");
        values.add("value17");
        values.add("value18");
        values.add("value19");
        values.add("value20");
        values.add("value21");
        System.out.println(values.size());
        System.out.println(values.isEmpty());
        System.out.println(Arrays.stream(values.toArray()).toList());

        values.remove("value2");
        System.out.println(Arrays.stream(values.toArray()).toList());

        values.remove(null);
        System.out.println(Arrays.stream(values.toArray()).toList());

        values.remove("2");
        System.out.println(Arrays.stream(values.toArray()).toList());
    }
}
