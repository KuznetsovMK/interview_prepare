package lesson_3.task_1;

/**
 * 1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
 */

public class PingPong {
    private static boolean ping = true;
    private static int counter = 10;

    public static void main(String[] args) {
        new Thread(() -> print(ping)).start();
        new Thread(() -> print(ping)).start();
    }


    private static synchronized void print(boolean flag) {
        while (counter > 0) {
            System.out.println(
                    flag
                            ? "ping"
                            : "pong"
            );

            ping = !flag;
            counter--;

            PingPong.class.notify();
            try {
                PingPong.class.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        PingPong.class.notifyAll();
    }
}
