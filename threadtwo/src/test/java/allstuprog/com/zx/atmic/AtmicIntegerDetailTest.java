package allstuprog.com.zx.atmic;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class AtmicIntegerDetailTest {

  @Test
  public void testCreate() {
    //无参构造
    AtomicInteger i = new AtomicInteger();
    //获取初始值，无参构造初始值为0
    System.out.println(i.get());
    //有参构造，赋初值10
    i = new AtomicInteger(10);
    System.out.println(i.get());
    //为AtomicInteger设置一个值
    i.set(11);
    System.out.println(i.get());
    //当你使用该变量时，再为该变量设置值
    i.lazySet(13);
    System.out.println(i.get());

    //获取变量的值后，再在该变量当前值的基础上增加一个指定的值
    AtomicInteger getAndAdd = new AtomicInteger(10);
    getAndAdd.getAndSet(10);
    int result = getAndAdd.getAndAdd(10);
    System.out.println("result = " + result);
    System.out.println(getAndAdd.get());
  }
}
