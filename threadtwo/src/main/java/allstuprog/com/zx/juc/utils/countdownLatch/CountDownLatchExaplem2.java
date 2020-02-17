package allstuprog.com.zx.juc.utils.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchExaplem2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/12 16:44
 * @Version 1.0
 */
public class CountDownLatchExaplem2 {

  final static CountDownLatch LATCH = new CountDownLatch(1);

  public static void main(String[] args) throws InterruptedException {
    final CountDownLatch LATCH = new CountDownLatch(1);

    new Thread() {
      @Override
      public void run() {
        System.out.println("do some initial work");
        try {
          Thread.sleep(1000);
          LATCH.await();
          System.out.println("do other working ......");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();

    new Thread(()->{
      try {
        LATCH.await();
        System.out.println("release!");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }).start();

    //等待该线程执行完成后，上面两个线程再执行await()方法后面的逻辑
    new Thread(()->{
      try {
        System.out.println("asyn prepare for some date");
        Thread.sleep(3000);
        System.out.println("date prepare for done");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }finally {
        LATCH.countDown();
      }
    }).start();

    Thread.currentThread().join();
  }
}
