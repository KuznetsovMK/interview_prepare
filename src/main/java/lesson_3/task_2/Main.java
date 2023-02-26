package lesson_3.task_2;

/**
 * 2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();

        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    counter.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
