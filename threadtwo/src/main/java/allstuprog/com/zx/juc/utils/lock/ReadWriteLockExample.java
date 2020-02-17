package allstuprog.com.zx.juc.utils.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockExample
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/15 17:30
 * @Version 1.0
 */
public class ReadWriteLockExample {

  private final static ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock(true);

  //获取读锁，在读取数据时使用
  private final static Lock READ_LOCK = READ_WRITE_LOCK.readLock();
  //获取写锁，在写入数据时使用
  private final static Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

  private final static List<Long> DATA = new ArrayList<Long>();

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(ReadWriteLockExample::read);
    thread1.start();

    TimeUnit.SECONDS.sleep(1);

    Thread thread2 = new Thread(() -> read());
    thread2.start();
  }

  public static void write() {
    try {
      WRITE_LOCK.lock();
      Long time = System.currentTimeMillis();
      DATA.add(time);
      System.out.println(Thread.currentThread().getName() + " add data is " + time);
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      WRITE_LOCK.unlock();
    }
  }

  public static void read() {
    try {
      READ_LOCK.lock();
      DATA.forEach(System.out::println);
      System.out.println(Thread.currentThread().getName() + "============");
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      READ_LOCK.unlock();
    }
  }
}
