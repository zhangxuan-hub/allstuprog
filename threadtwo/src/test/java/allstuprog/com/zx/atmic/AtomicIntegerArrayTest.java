package allstuprog.com.zx.atmic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import org.junit.Test;

/**
 * @ClassName AtomicIntegerArrayTest
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/10 15:40
 * @Version 1.0
 */
public class AtomicIntegerArrayTest {

  @Test
  public void testCreateAtomicIntegerArray() {
    AtomicIntegerArray array = new AtomicIntegerArray(10);
    System.out.println(array.length());
  }
}
