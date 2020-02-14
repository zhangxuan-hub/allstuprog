package allstuprog.com.zx.juc.utils.lock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import javax.swing.text.html.Option;

/**
 * @ClassName ReentrantLockExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/14 19:44
 * @Version 1.0
 */
public class ReentrantLockExample1 {

  private final static Lock LOCK = new ReentrantLock();

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread("T1") {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + " in.");
//        needLock();
//        testUnInterruptibly();
        needLockBySync();
      }
    };
    t1.start();
    TimeUnit.SECONDS.sleep(1);

    Thread t2 = new Thread("T2") {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + " in.");
//        needLock();
//        testUnInterruptibly();
        needLockBySync();
      }
    };
    t2.start();

    TimeUnit.SECONDS.sleep(1);

    t2.interrupt();
    System.out.println("main thread finished.");
  }

  public static void needLock() {
    try {
      //LOCK.lock()方法和加synchronized关键字相同，在未抢到锁时俊不能被打断
      LOCK.lock();
      Optional
          .of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.")
          .ifPresent(System.out::println);
      while (true) {
      }
    } finally {
      LOCK.unlock();
      Optional.of("The thread-" + Thread.currentThread().getName() + " release lock.")
          .ifPresent(System.out::println);
    }
  }

  public static void testUnInterruptibly() {
    try {
      //调用LOCK.lockInterruptibly()锁时，没有抢到该锁处于block状态的线程可以被打断
      LOCK.lockInterruptibly();
      Optional
          .of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.")
          .ifPresent(System.out::println);
      while (true) {
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
      Optional.of("The thread-" + Thread.currentThread().getName() + " release lock.")
          .ifPresent(System.out::println);
    }
  }

  public static void needLockBySync() {
    synchronized (ReentrantLockExample1.class) {
      while (true) {
      }

    }
  }
}
