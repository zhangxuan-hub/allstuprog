package allstuprog.com.zx.juc.utils.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName ThreadPoolExecutorLongTimeTask
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/18 19:54
 * @Version 1.0
 */
public class ThreadPoolExecutorLongTimeTask {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(5),
        r -> {
          Thread t = new Thread(r);
          t.setDaemon(true);
          return t;
        },new ThreadPoolExecutor.AbortPolicy());

    IntStream.rangeClosed(1, 10).boxed().forEach(i->
      executorService.submit(()->{
        while(true) {}
      })
    );

    executorService.shutdownNow();
    executorService.awaitTermination(5, TimeUnit.SECONDS);
    System.out.println("=====start the sequence working=====");
  }
}
