package allstuprog.com.zx.multithreading3.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampReferenceTest {

  //定义一个AtomicStampedReference，设置初始值为100(该初始值会保存在compareAndSet()所在类中的value变量中)，
  //初始stamp(标签)为0
  private static AtomicStampedReference<Integer> atomicRef =
      new AtomicStampedReference<Integer>(100, 0);

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
        //当试图通过atomicRef的compareAndSet()方法，将AtomicStampedReference中保存的值修改为101时，首先要看：
        //1.传入的期望值是否与compareAndSet()方法所在类中的value变量的值相等
        //2.如果相等，再看传入的传入的stamp是否与compareAndSet()方法所在类中的stamp的值相等，
        //3.如果相等再用传入的newStamp值替换compareAndSet()方法中的stamp的值。

        //这种修改方式本质上就是乐观锁。
        Boolean success = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
        System.out.println(success);
        System.out.println("Thread-t1: " + atomicRef.getReference());

        success = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
        System.out.println(success);

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        //为测试方便先将atomicRef中的stamp的值进行保存，
        int stamp = atomicRef.getStamp();
        System.out.println("Before sleep: stamp=" + stamp);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("After sleep: stamp=" + atomicRef.getStamp());

        //虽然期望值和AtomicStampedReference中保存的值相等，但线程t1已经将AtomicStampedReference中stamp的值改成了2，所以修改会失败
        Boolean success = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), stamp + 1);
        System.out.println(success);
        System.out.println(atomicRef.getReference());

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
