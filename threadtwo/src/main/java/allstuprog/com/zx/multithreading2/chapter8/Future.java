package allstuprog.com.zx.multithreading2.chapter8;

public interface Future<T> {
  T get() throws InterruptedException;
}
