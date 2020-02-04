package allstuprog.com.zx.multithreading2.chapter9;

import java.util.LinkedList;

public class RequestQueue {

  private final LinkedList<Request> queue = new LinkedList<Request>();

  public Request getRequest() {
    synchronized (queue) {
      while (queue.size() <= 0) {
        try {
          queue.wait();

        } catch (InterruptedException e) {
          return null;
        }
      }
    }
    return queue.removeFirst();
  }

  public void putRequest(Request request) {
    synchronized (queue) {
      queue.addLast(request);
      queue.notifyAll();
    }
  }
}
