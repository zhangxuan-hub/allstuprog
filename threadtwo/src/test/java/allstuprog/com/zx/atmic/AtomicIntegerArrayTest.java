package allstuprog.com.zx.atmic;

import static org.junit.Assert.*;

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

  private AtomicIntegerArray array = new AtomicIntegerArray(10);

  @Test
  public void testCreateAtomicIntegerArray() {
    assertEquals(10, array.length());
  }

  @Test
  public void testGet() {
    assertEquals(0, array.get(0));
  }

  @Test
  public void testSet() {
    array.set(5, 5);
    assertEquals(10, array.length());
    assertEquals(5, array.get(5));
  }

  @Test
  public void testGetAndSet() {
    array.set(5, 5);
    assertEquals(5, array.get(5));
    array.getAndSet(5, 6);
    assertEquals(6, array.get(5));
  }

  @Test
  public void testSetAndGet() {
    array.set(5, 5);
    assertEquals(5, array.get(5));
    array.getAndSet(5, 6);
    assertEquals(6, array.get(5));
  }
}
