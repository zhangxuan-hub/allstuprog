package allstuprog.com.zx.multithreading2.chapter15;

public class CounterTest {

  public static void main(String[] args) throws InterruptedException {
    CounterIncrement counterIncrement = new CounterIncrement();
    counterIncrement.start();

    Thread.sleep(10000L);
    counterIncrement.close();
  }
}
