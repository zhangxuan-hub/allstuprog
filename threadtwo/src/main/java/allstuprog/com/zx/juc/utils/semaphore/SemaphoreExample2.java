package allstuprog.com.zx.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName SemaphoreExample2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/14 16:57
 * @Version 1.0
 */
public class SemaphoreExample2 {

  public static void main(String[] args) throws InterruptedException {
    //设置通行证(通行证可以理解为锁)的个数，即：同一时刻能够持有该锁的对象的个数。
    //一个对象一次性也可以获取n个锁，但释放时也需要将这个n个锁同时释放掉，但获取的锁的个数不能大于初始化时设置的锁的个数
    Semaphore semaphore = new Semaphore(1);
    IntStream.rangeClosed(1, 3).forEach(i->{
      new Thread(()->{
        System.out.println(Thread.currentThread().getName() + " in.");
        try {
          //通过semaphore.acquire(int size)获取锁，size是一次性获取的锁的个数，不传默认获取一个
          semaphore.acquire(1);
          System.out.println(Thread.currentThread().getName() + " get #semaphore.");
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          //释放锁
          semaphore.release(1);
        }

        System.out.println(Thread.currentThread().getName() + " out.");
      }, String.valueOf(i)).start();
    });

    while(true) {
      System.out.println("当前可用的许可证(锁)的数量: " + semaphore.availablePermits());
      System.out.println("当前正在等待获取许可证(锁)的对象的个数: " + semaphore.getQueueLength());
      System.out.println("=================================");

      TimeUnit.SECONDS.sleep(1);
    }
  }
}
