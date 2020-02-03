package allstuprog.com.zx.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducterThread extends Thread {

  private final MessageQueue queue;

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  private final static AtomicInteger counter = new AtomicInteger(0);

  public ProducterThread(MessageQueue queue, int seq) {
    super("PRODUCTER-" + seq);
    this.queue = queue;
  }


  @Override
  public void run() {
    while (true) {
      try {
        Message message = new Message("Message-" + counter.getAndIncrement());
        queue.put(message);
        System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
        Thread.sleep(RANDOM.nextInt(1000));

      } catch (InterruptedException e) {
        break;
      }
    }
  }
}
