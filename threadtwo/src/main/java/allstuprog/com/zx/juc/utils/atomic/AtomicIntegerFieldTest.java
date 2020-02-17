package allstuprog.com.zx.juc.utils.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.IntStream;

/**
 * @ClassName AtomicIntegerFieldTest
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/12 8:55
 * @Version 1.0
 */
public class AtomicIntegerFieldTest {

  public static void main(String[] args) {
    //将需要保证原子性变量的类的类名.class及属性名,分别赋值给AtomicIntegerFieldUpdater.newUpdater(xxx.classm, 变量名)
    //下面以TestMe类中i变量为例说明
    AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
    TestMe testMe = new TestMe();
    IntStream.rangeClosed(0,1).forEach(i->{
      new Thread(()->{
        final int max = 20;
        for(int j=0; j<max; j++) {
          int res = updater.getAndIncrement(testMe);
          System.out.println(Thread.currentThread().getName() + "==>" + res);
        }
      }, String.valueOf(i)).start();
    });
  }

  static class TestMe {
    volatile int i;
  }
}
