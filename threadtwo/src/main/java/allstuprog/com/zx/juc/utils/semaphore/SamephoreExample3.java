package allstuprog.com.zx.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SamephoreExample3
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/14 17:27
 * @Version 1.0
 */
public class SamephoreExample3 {

  public static void main(String[] args) throws InterruptedException {
    final Semaphore semaphore = new Semaphore(1);

    Thread t1 = new Thread("T1"){
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "is running");
        try {
          //获取许可证后不能被中断.
          semaphore.acquireUninterruptibly();
        } finally {
          semaphore.release();
        }

        System.out.println("T2 finished.");
      }
    };
    t1.start();

    TimeUnit.MILLISECONDS.sleep(50);
    t1.interrupt();
  }
}
