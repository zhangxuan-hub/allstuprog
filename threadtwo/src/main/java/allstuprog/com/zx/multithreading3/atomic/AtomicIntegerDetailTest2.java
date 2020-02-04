package allstuprog.com.zx.multithreading3.atomic;

import java.util.stream.IntStream;

public class AtomicIntegerDetailTest2 {

  private final static CompareAndSetLock LOCK = new CompareAndSetLock();

  public static void main(String[] args) {
    IntStream.rangeClosed(1, 5).forEach(
        i->new Thread(()->{
          try {
            doSomething();
          } catch (InterruptedException | GetLockException e) {
            e.printStackTrace();
          }
        }, "Thread-" + String.valueOf(i)).start()
    );
  }

  public static void doSomething() throws InterruptedException, GetLockException {
    try{
      LOCK.tryLock();
      System.out.println(Thread.currentThread().getName() + " get the lock.");
      Thread.sleep(100_000);
    }finally {
      LOCK.unLock();
    }

  }
}
