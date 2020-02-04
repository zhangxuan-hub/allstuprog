package allstuprog.com.zx.multithreading2.chapter9;

import java.util.Random;

public class ClientThread extends Thread {

  private final RequestQueue  queue;
  private final Random random;
  private final String sendValue;

  public ClientThread(RequestQueue queue, String sendValue) {
    this.queue = queue;
    this.sendValue = sendValue;
    random = new Random(System.currentTimeMillis());
  }

  @Override
  public void run() {
    for(int i=0; i<5; i++) {
      try{
        System.out.println("Client -> request: " + sendValue);
        queue.putRequest(new Request(sendValue));
        Thread.sleep(random.nextInt(1000));

      }catch (Exception e){
        e.printStackTrace();
      }
    }
  }
}
