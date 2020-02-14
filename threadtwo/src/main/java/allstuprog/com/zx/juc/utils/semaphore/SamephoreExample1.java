package allstuprog.com.zx.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName SamephoreExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/14 16:08
 * @Version 1.0
 */
public class SamephoreExample1 {

  public static void main(String[] args) {
    SemaphoreLock lock = new SemaphoreLock();

    IntStream.rangeClosed(1, 2).forEach(i->
      new Thread(String.valueOf(i)){
        @Override
        public void run() {
          try {
            System.out.println(Thread.currentThread().getName() + " is running. ");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get the #SemaphoreLock.");
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " get the #release.");
          }
        }

    }.start());
  }

  static class SemaphoreLock {

    //同一时刻只有一个方法能够持有该锁
    private Semaphore semaphore = new Semaphore(1);

    public void lock() throws InterruptedException {
      semaphore.acquire();
    }

    public void unlock() {
      semaphore.release();
    }
  }
}
