package allstuprog.com.zx.juc.utils.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

//  private static volatile int value = 0;

  private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

  public static void main(String[] args) throws InterruptedException {
    /*Thread t1 = new Thread() {
      @Override
      public void run() {
        int x = 0;
        while (x < 500) {
          set.add(value);
          int tmp = value;
          System.out.println(Thread.currentThread().getName() + ":" + tmp);
          value += 1;
          x++;
        }
      }
    };

    Thread t2 = new Thread() {
      @Override
      public void run() {
        int x = 0;
        while (x < 500) {
          set.add(value);
          int tmp = value;
          System.out.println(Thread.currentThread().getName() + ":" + tmp);
          value += 1;
          x++;
        }
      }
    };

    Thread t3 = new Thread() {
      @Override
      public void run() {
        int x = 0;
        while (x < 500) {
          set.add(value);
          int tmp = value;
          System.out.println(Thread.currentThread().getName() + ":" + tmp);
          value += 1;
          x++;
        }
      }
    };

    t1.start();
    t2.start();
    t3.start();
    t1.join();
    t2.join();
    t3.join();

    System.out.println(set.size());*/

//    final AtomicInteger value = new AtomicInteger(0);
//    new Thread() {
//      @Override
//      public void run() {
//        for (int i = 0; i < 10; i++) {
//          int v = value.getAndAdd(1);
//          System.out.println(Thread.currentThread().getName() + "：" + v);
//          try {
//            Thread.sleep(100);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    }.start();
//    new Thread() {
//      @Override
//      public void run() {
//        for (int i = 0; i < 10; i++) {
//          int v = value.getAndAdd(1);
//          System.out.println(Thread.currentThread().getName() + ":" + v);
//          try {
//            Thread.sleep(100);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    }.start();
//    Thread t1 = new Thread() {
//      @Override
//      public void run() {
//        int x = 0;
//        while (x < 500) {
//          int v = value.getAndIncrement();
//          set.add(v);
//          System.out.println(Thread.currentThread().getName() + ":" + v);
//          x++;
//        }
//      }
//    };
//    Thread t2 = new Thread() {
//      @Override
//      public void run() {
//        int x = 0;
//        while (x < 500) {
//          int v = value.getAndIncrement();
//          set.add(v);
//          System.out.println(Thread.currentThread().getName() + ":" + v);
//          x++;
//        }
//      }
//    };
//    Thread t3 = new Thread() {
//      @Override
//      public void run() {
//        int x = 0;
//        while (x < 500) {
//          int v = value.getAndIncrement();
//          set.add(v);
//          System.out.println(Thread.currentThread().getName() + ":" + v);
//          x++;
//        }
//      }
//    };
//
//    t1.start();
//    t2.start();
//    t3.start();
//    t1.join();
//    t2.join();
//    t3.join();
//
//    System.out.println(set.size());

    AtomicInteger i = new AtomicInteger(12);
    boolean b = i.compareAndSet(12, 20);
    System.out.println(i.get());
    System.out.println(b);
  }

}
