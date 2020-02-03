package allstuprog.com.zx.chapter9;

public class SuspensionClient {

  private static final RequestQueue queue = new RequestQueue();

  public static void main(String[] args) {
    new ClientThread(queue, "zhangxuan").start();
    new ClientThread(queue, "lisi").start();
    new ClientThread(queue, "wangwu").start();
    new ClientThread(queue, "sunliu").start();
    new ClientThread(queue, "zhubajie").start();
    new ClientThread(queue, "lisi").start();

    ServiceThread t1 = new ServiceThread(queue);
    ServiceThread t2 = new ServiceThread(queue);
    t1.start();
    t2.start();

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t1.close();
    t2.close();
  }
}
