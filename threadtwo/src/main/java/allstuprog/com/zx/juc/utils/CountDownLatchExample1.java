package allstuprog.com.zx.juc.utils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatchExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/12 12:56
 * @Version 1.0
 */
public class CountDownLatchExample1 {

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  private static ExecutorService executor = Executors.newFixedThreadPool(2);

  //定义CountDownLatch并设置消减线程数。
  //当调用LATCH.countDown()方法时，设置的数字会被减少，当减到0时，执行LATCH.await()后面的逻辑
  private final static CountDownLatch LATCH = new CountDownLatch(10);

  public static void main(String[] args) throws InterruptedException {
    int[] data = query();

    for (int i = 0; i < data.length; i++) {
      executor.execute(new SimpleRunnable(data, i, LATCH));
    }
    //当初始化CountDownLatch时设置的数字减到0时，执行LATCH.await()后面的逻辑
    LATCH.await();
    //awaitTermination()方法为线程池设置等待时间，如果未达到等待时间，线程已经全部运行完成，则结束线程池。
//    executor.awaitTermination(1, TimeUnit.HOURS);
    System.out.println("all of work finish done.");
    //shutdown()方法只会将线程池中空闲线程打断
    executor.shutdown();
  }

  static class SimpleRunnable implements Runnable {

    private final int[] data;

    private final int index;

    private final CountDownLatch LATCH;

    SimpleRunnable(final int[] data, final int index, final CountDownLatch LATCH) {
      this.data = data;
      this.index = index;
      this.LATCH = LATCH;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(RANDOM.nextInt(2000));

        int val = data[index] % 2;
        if (val % 2 == 0) {
          data[index] = val * 2;
        } else {
          data[index] = val * 10;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getName() + " is finised.");
      //当调用LATCH.countDown()方法时，初始化CountDownLatch时设置的数字会被减少，当减到0时，线程池结束
      LATCH.countDown();
    }
  }

  private static int[] query() {
    return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  }
}
