package allstuprog.com.zx.juc.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionExample
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/15 18:19
 * @Version 1.0
 */
public class ConditionExample {

  private final static ReentrantLock LOCK = new ReentrantLock(true);

  private final static Condition CONDITION = LOCK.newCondition();

  //共享数据
  private static int data = 0;

  /**
   * 数据是否被使用，true: 未被使用；false: 已经被使用
   */
  private static volatile boolean noUse = true;


  public static void main(String[] args) {
    new Thread(() -> {
      for (; ; ) {
        buildData();
      }
    }).start();

    for(int i=0; i<2; i++) {
      new Thread(()->{
        for(;;) {
          useData();
        }
      }).start();
    }
  }

  private static void buildData() {
    try {
      LOCK.lock();//相当于synchronized
      while (noUse) {
        CONDITION.await(); //相当于monitor.wait();
      }

      data++;
      Optional.of("P:" + data).ifPresent(System.out::println);
      TimeUnit.SECONDS.sleep(1);

      noUse = true;
      CONDITION.signalAll();//相当于monitor.notify
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();//相当于相当于synchronized end.
    }
  }

  private static void useData() {
    try {
      LOCK.lock();
      while (!noUse) {
        CONDITION.await();
      }
      Optional.of(Thread.currentThread().getName() + " use " + data).ifPresent(System.out::println);
      TimeUnit.SECONDS.sleep(1);

      noUse = false;
      CONDITION.signalAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
    }
  }
}
