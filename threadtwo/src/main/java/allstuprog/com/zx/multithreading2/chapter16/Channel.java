package allstuprog.com.zx.multithreading2.chapter16;

import java.util.Arrays;

public class Channel {

  private final static int MAX_REQUEST = 100;

  private final Request[] requestQueue;

  private int head;

  private int tail;

  private int count;

  private final WorkerThread[] workerPool;

  public Channel(int workers) {
    this.requestQueue = new Request[MAX_REQUEST];
    this.head = 0;
    this.tail = 0;
    this.count = 0;
    this.workerPool = new WorkerThread[workers];
    this.init();

  }

  public void init() {
    for (int i = 0; i < workerPool.length; i++) {
      workerPool[i] = new WorkerThread("Worker-" + i, this);
    }
  }

  /**
   * push switch to start all of worker to work
   */
  public void startWorkers() {
    Arrays.asList(workerPool).forEach(WorkerThread::start);
  }

  public synchronized void put(Request request) {
    while (count >= requestQueue.length) {
      try {
        this.wait();
      } catch (InterruptedException e) {
      }
    }
    this.requestQueue[tail] = request;
    tail = (tail + 1) % requestQueue.length;
    this.count++;
    this.notifyAll();
  }

  public synchronized Request take() {
    while (count <= 0) {
      try {
        this.wait();
      } catch (InterruptedException e) {
      }
    }

    Request request = this.requestQueue[head];
    head = (head + 1) % requestQueue.length;
    this.count--;
    this.notifyAll();
    return request;
  }
}
