package allstuprog.com.zx.juc.utils.condition;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @ClassName ConditionExample2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/15 19:57
 * @Version 1.0
 */
public class ConditionExample2 {

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  private final static ReentrantLock LOCK = new ReentrantLock();

  private final static Condition PRODUCECOND = LOCK.newCondition();

  private final static Condition CONSUMECOND = LOCK.newCondition();

  private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<Long>();

  private final static int MAX_CAPACITY = 100;

  private static volatile boolean noUse = true;

  public static void main(String[] args) {
    IntStream.rangeClosed(1, 4).boxed().forEach(ConditionExample2::beginProd);
    IntStream.rangeClosed(1, 13).boxed().forEach(ConditionExample2::beginCons);

  }

  private static void beginProd(int i) {
    new Thread(() -> {
      for (int j = 0; j < 6; j++) {
        produce();
        mySleep(2);
      }
    }, String.valueOf("P-" + i)).start();
  }

  private static void beginCons(int i) {
    new Thread(() -> {
      for (int j = 0; j < 6; j++) {
        consume();
        mySleep(2);
      }
    }, String.valueOf("C-" + i)).start();
  }

  private static void produce() {
    try {
      LOCK.lock();
      while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
        PRODUCECOND.await();
      }
      //当前处于condition的waitSet队列中的线程数
      System.out.println("PRODUCECOND.WaitQueueLength: " + LOCK.getWaitQueueLength(PRODUCECOND));
      //当前condition的waitSet是否有正在等待的线程，有：返回true，无：返回false
      System.out.println("PRODUCECOND.hasWaiters: " + LOCK.hasWaiters(PRODUCECOND));

      Long value = System.currentTimeMillis();
      TIMESTAMP_POOL.addLast(value);
      Optional.of(Thread.currentThread().getName() + "-" + value).ifPresent(System.out::println);

      mySleep(2);
      CONSUMECOND.signalAll();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
    }
  }

  private static void consume() {
    try {
      LOCK.lock();
      while (TIMESTAMP_POOL.size() <= 0 || TIMESTAMP_POOL.isEmpty()) {
        CONSUMECOND.await();
      }

      System.out.println("CONSUMECOND.WaitQueueLength: " + LOCK.getWaitQueueLength(CONSUMECOND));
      System.out.println("CONSUMECOND.hasWaiters: " + LOCK.hasWaiters(CONSUMECOND));

      Long value = TIMESTAMP_POOL.removeFirst();
      Optional.of(Thread.currentThread().getName() + "-" + value)
          .ifPresent(System.out::println);
      mySleep(2);
      PRODUCECOND.signalAll();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
    }
  }

  private static void mySleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
