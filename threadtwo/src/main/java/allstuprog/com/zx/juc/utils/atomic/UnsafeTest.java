package allstuprog.com.zx.juc.utils.atomic;

import sun.misc.Unsafe;

/**
 * @ClassName UnsafeTest
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/12 10:12
 * @Version 1.0
 */
public class UnsafeTest {

  public static void main(String[] args) {
    Unsafe unsafe = Unsafe.getUnsafe();
    System.out.println(unsafe);
  }
}
