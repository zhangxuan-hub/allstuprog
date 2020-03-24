package allstuprog.com.zx.juc.utils.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExcecutorServiceExample1 {

  public static void main(String[] args) {
    isTerminated();
  }

  /**
   * {@link ExecutorService#isShutdown()}
   * isShutDown(): 该方法用于判断线程是否关闭
   * 注意：线程池一旦关闭(即：执行了shutDown()方法)，就无法再执行任务。
   */
  private static void isShutDown() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println(executorService.isShutdown());
    executorService.shutdown();
    System.out.println(executorService.isShutdown());
    executorService.execute(()-> System.out.println("我将被执行吗？"));
  }

  /**
   * {@link ExecutorService#isTerminated()}
   * isTerminated(): 判断线程池是否已经被关闭
   * {@link ThreadPoolExecutor#isTerminating()}
   * isTerminating()：判断线程池是否正在关闭中。
   */
  private static void isTerminated() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    executorService.execute(()->{
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    executorService.shutdown();
    System.out.println(executorService.isShutdown());
    System.out.println(executorService.isTerminated());
    System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
  }
}
