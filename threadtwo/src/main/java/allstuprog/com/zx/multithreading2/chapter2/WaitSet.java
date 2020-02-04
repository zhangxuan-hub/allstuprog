package allstuprog.com.zx.multithreading2.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {
    //定义一个显式锁
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(
                i -> new Thread(() -> {
                    synchronized (LOCK) {
                        try {
                            Optional.of(Thread.currentThread().getName() + "will come to wait set").ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + "will leave to wait set").ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, String.valueOf(i)).start()
        );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.rangeClosed(1, 10).forEach(
                i -> {
                    synchronized (LOCK) {
                        LOCK.notify();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
