package allstuprog.com.zx.chapter10;

import java.util.Random;

public class ThreadLocalComplexTest {

  private final static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      THREAD_LOCAL.set("Thread->T1");
      try {
        Thread.sleep(RANDOM.nextInt(1000));
        System.out.println(Thread.currentThread().getName() + "->" + THREAD_LOCAL.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      THREAD_LOCAL.set("Thread->T2");
      try {
        Thread.sleep(RANDOM.nextInt(1000));
        System.out.println(Thread.currentThread().getName() + "->" + THREAD_LOCAL.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    t1.start();
    t2.start();
    t1.join();
    t2.join();

    System.out.println("=================");
    System.out.println(Thread.currentThread().getName() + "->" + THREAD_LOCAL.get());
  }

}
