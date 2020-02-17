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
 * @ClassName StampedLockExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/16 15:39
 * @Version 1.0
 */
public class StampedLockExample1 {

  private final static StampedLock LOCK = new StampedLock();

  private final static List<Long> DATA = new ArrayList<Long>();

  public static void main(String[] args) {
    final ExecutorService executor = Executors.newFixedThreadPool(10);

    Runnable readTask = () -> {
      for (; ; ) {
        read();
      }
    };

    Runnable writeTask = () -> {
      for (; ; ) {
        write();
      }
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
    executor.submit(writeTask);
  }

  public static void read() {

    long stamped = -1;

    try {
      stamped = LOCK.readLock();
      Optional.of(DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", "")))
          .ifPresent(System.out::println);
      TimeUnit.SECONDS.sleep(1);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock(stamped);
    }
  }

  private static void write() {
    long stamped = -1;
    try {
      stamped = LOCK.writeLock();
      Long value = System.currentTimeMillis();
      System.out.println("W-" + value);
      DATA.add(value);
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock(stamped);
    }
  }
}
