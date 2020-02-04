package allstuprog.com.zx.atmic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.Test;

public class AtomicBooleanTest {

  @Test
  public void testCreateWithOutArguments() {
    AtomicBoolean bool = new AtomicBoolean();
    assertFalse(bool.get());
  }

  @Test
  public void testGetAndSet() {
    AtomicBoolean bool = new AtomicBoolean(true);
    boolean result = bool.getAndSet(false);
    assertTrue(result);
    assertFalse(bool.get());
  }

  @Test
  public void testCompareAndSet() {
    AtomicBoolean bool = new AtomicBoolean(true);
    boolean result = bool.compareAndSet(true, false);
    assertTrue(result);
    assertFalse(bool.get());
  }

  public void testCompareAndSetFailed() {
    AtomicBoolean bool = new AtomicBoolean(true);
    boolean result = bool.compareAndSet(false, true);
    assertFalse(result);
    assertTrue(bool.get());

  }
}
