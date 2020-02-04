package allstuprog.com.zx.multithreading2.chapter14;

public class CountDown {
  private final int total;
  private int counter;

  public CountDown(int total) {
    this.total = total;
  }

  public void down() {
    synchronized (this) {
      counter++;
      this.notifyAll();
    }
  }

  public void await() {
    synchronized (this) {
      while(counter!=total) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
