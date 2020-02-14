package allstuprog.com.zx.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SamephoreExample4
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/14 19:08
 * @Version 1.0
 */
public class SamephoreExample4 {

  public static void main(String[] args) throws InterruptedException {
    Semaphore semaphore = new Semaphore(5);

    Thread t1 = new Thread() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        //获取所有许可证
        semaphore.drainPermits();
        System.out.println(semaphore.availablePermits());
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          semaphore.release();
        }
        System.out.println(Thread.currentThread().getName() + " out.");
      }
    };
    t1.start();

    TimeUnit.SECONDS.sleep(1);

    Thread t2 = new Thread() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        // 获取许可证(锁)，返回获取结果，成功: true; 失败: false
        // semaphore.tryAcquire(long timeout, TimeUnit unit):尝试在指定时间内获取许可证(锁)，并返回获取结果。
        // 如果不设置时间，默认立刻获取，然后返回结果。
        boolean successful = false;
        try {
          successful = semaphore.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          semaphore.release();
        }
        System.out.println(successful?"successful":"failed");

        System.out.println(Thread.currentThread().getName() + " out.");
      }
    };
    t2.start();

    TimeUnit.SECONDS.sleep(1);
    System.out.println("是否有线程正在等待获取许可证(锁): " + semaphore.hasQueuedThreads());
  }
}
