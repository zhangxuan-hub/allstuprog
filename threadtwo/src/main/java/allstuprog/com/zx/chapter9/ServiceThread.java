package allstuprog.com.zx.chapter9;

import java.util.Random;

public class ServiceThread extends Thread {

  private final RequestQueue queue;
  private final Random random;
  private volatile boolean flag = true;

  public ServiceThread(RequestQueue queue) {
    this.queue = queue;
    random = new Random(System.currentTimeMillis());
  }

  @Override
  public void run() {
    while (flag) {
      try {
        Request request = queue.getRequest();
        if(null==request) {
          System.out.println("Received the empty request.");
          continue;
        }
        System.out.println("Server -> " + request.getValue());
        Thread.sleep(random.nextInt(1000));

      } catch (Exception e) {
        return;
      }
    }
  }

  public void close() {
    flag = false;
    this.interrupt();
  }
}
