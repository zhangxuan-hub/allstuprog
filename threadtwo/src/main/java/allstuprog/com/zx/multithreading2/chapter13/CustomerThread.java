package allstuprog.com.zx.multithreading2.chapter13;

import java.util.Random;

public class CustomerThread extends Thread {

  private final MessageQueue queue;

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  private final int seq;

  public CustomerThread(MessageQueue queue, int seq) {
    this.queue = queue;
    this.seq = seq;
  }

  @Override
  public void run() {
    while(true) {
      try {
        Message message = queue.take();
        System.out.println(Thread.currentThread().getName() +" take a message: "+ message.getData());
        Thread.sleep(RANDOM.nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
