package lesson_3.task_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private Lock lock = new ReentrantLock();
    private int counter = 0;

    public synchronized void increment() throws InterruptedException {
        var isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);

        if (isLockAcquired) {
            try {
                System.out.println(String.format("Счетчик+ : %s -> %s", counter, ++counter));
            } finally {
                lock.unlock();
            }
        }

    }

    public synchronized void decrement() throws InterruptedException {
        var isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);

        if (isLockAcquired) {
            try {
                System.out.println(String.format("Счетчик- : %s -> %s", counter, --counter));
            } finally {
                lock.unlock();
            }
        }
    }
}
