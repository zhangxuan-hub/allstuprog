package allstuprog.com.zx.juc.utils.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @ClassName StampedLockExample2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/16 16:01
 * @Version 1.0
 */
public class StampedLockExample2 {

  //定义一个StampedLock锁
  private final static StampedLock LOCK = new StampedLock();

  //定义共享变量
  private final static List<Long> DATA = new ArrayList<Long>();

  public static void main(String[] args) {
    final ExecutorService executor = Executors.newFixedThreadPool(10);

    Runnable readTask = () -> {
      for (; ; ) {
        read();
      }
    };

    Runnable wirteTask = () -> {
      write();
    };

    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(readTask);
    executor.submit(wirteTask);
  }

  //读方法
  private static void read() {

    //获取乐观锁
    Long stamped = LOCK.tryOptimisticRead();

    //判断所是否已经被获取
    if (LOCK.validate(stamped)) {
      try {
        stamped = LOCK.readLock();
        System.err.println("R-STAMPED-" +stamped );
        Optional.of(DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", "")))
            .ifPresent(System.out::println);
        TimeUnit.SECONDS.sleep(1);

      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        LOCK.unlock(stamped);
      }
    }
  }

  private static void write() {

    long stamped = -1;

    try {
      stamped = LOCK.writeLock();
      System.err.println("W-STAMPED-" +stamped );
      long value = System.currentTimeMillis();
      Optional.of("W-" + value).ifPresent(System.out::println);
      DATA.add(value);
      TimeUnit.SECONDS.sleep(1);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock(stamped);
    }
  }
}
