package allstuprog.com.zx.chapter8;

public interface Future<T> {
  T get() throws InterruptedException;
}
