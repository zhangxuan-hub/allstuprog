package allstuprog.com.zx.atmic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.junit.Test;

/**
 * @ClassName FailedAtomicIntegerUpdaterTest
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/12 9:16
 * @Version 1.0
 */
public class FailedAtomicIntegerUpdaterTest {

  /**
   * 不能访问对象的私有属性
   *  错误：java.lang.RuntimeException: java.lang.IllegalAccessException: Class
   * allstuprog.com.zx.atmic.FailedAtomicIntegerUpdaterTest can not access a member of class
   * allstuprog.com.zx.atmic.TestMe with modifiers "private volatile"
   */
  @Test(expected = RuntimeException.class)
  public void testPrivateFieldAccesserError() {
    AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater
        .newUpdater(TestMe.class, "i");
    TestMe me = new TestMe();

    updater.compareAndSet(me, 0, 1);
  }

  /**
   * 被保证原子性的变量，必须被volatile关键字修饰
   * 错误：java.lang.IllegalArgumentException: Must be volatile type
   */
  @Test
  public void testFieldIsNoVolatile() {
    AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater
        .newUpdater(TestMe2.class, Integer.class, "i");
    TestMe2 me2 = new TestMe2();
    updater.compareAndSet(me2, null, 1);
  }

  static class TestMe2 {
    Integer i;
  }
}
