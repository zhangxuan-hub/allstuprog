package allstuprog.com.zx.multithreading2.chapter16;

import java.util.Random;

public class TransportThread extends Thread {

  private final Channel channel;

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  public TransportThread(String name, Channel channel) {
    super(name);
    this.channel = channel;
  }

  @Override
  public void run() {
    try{
      for(int i=0; true; i++) {
        Request request = new Request(getName(), i);
        this.channel.put(request);
        Thread.sleep(1_000l);
      }
    }catch(Exception e) {

    }
  }
}
