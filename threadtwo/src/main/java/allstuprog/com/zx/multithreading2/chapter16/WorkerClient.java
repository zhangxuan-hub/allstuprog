package allstuprog.com.zx.multithreading2.chapter16;

public class WorkerClient {

  public static void main(String[] args) {
    final Channel channel = new Channel(5);
    channel.startWorkers();

    new TransportThread("zhangxuan", channel).start();
    new TransportThread("lisi", channel).start();
    new TransportThread("wangwu", channel).start();
    new TransportThread("sunliu", channel).start();
    new TransportThread("qimazi", channel).start();
  }
}
