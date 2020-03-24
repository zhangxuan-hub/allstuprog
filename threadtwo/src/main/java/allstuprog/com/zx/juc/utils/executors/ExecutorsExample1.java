package allstuprog.com.zx.juc.utils.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName ExecutorsExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/19 8:27
 * @Version 1.0
 */
public class ExecutorsExample1 {

  public static void main(String[] args) throws InterruptedException {
//    useCachedThreadPool();
//    useFixedSizePool();
    useSinglePool();
  }

  /**
   *  new FinalizableDelegatedExecutorService
   *             (new ThreadPoolExecutor(1, 1,
   *                                     0L, TimeUnit.MILLISECONDS,
   *                                     new LinkedBlockingQueue<Runnable>()));
   * 由于该线程池的核心线程数和最大线程数均为1，故该线程池只会启动一个线程, 且如果该线程池不关闭，这个线程永远存活。
   * LinkedBlockingQueue的长度为Integer.MAX_VALUE
   * 该线程是被转换为FinalizableDelegatedExecutorService，在使用时不能将其转换为ThreadPoolExector
   */
  private static void useSinglePool() throws InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    IntStream.rangeClosed(1, 100).boxed().forEach(i->{
      executorService.execute(()->{
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" [ "+ i +" ]" );
      });
    });

    TimeUnit.SECONDS.sleep(1);
  }

  /**
   * new ThreadPoolExecutor
   *          (nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
   *           new LinkedBlockingQueue<Runnable>());
   * LinkedBlockingQueue的长度为Integer.MAX_VALUE
   * 由于核心线程数不为0，故该线程池不会自动关闭
   * 由于核心线程数和最大线程数相等，故该线程池不会自动回收线程
   */
  private static void useFixedSizePool() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

    IntStream.rangeClosed(1, 100).boxed().forEach(i->{
      executorService.execute(()->{
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" [ "+ i +" ]" );
      });
    });

    TimeUnit.SECONDS.sleep(1);

    System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
  }

  private static void useCachedThreadPool() throws InterruptedException {
    /**
     * 1.Executors.newCachedThreadPool()线程池适用于执行短周期的任务，即：每个任务的执行时间均不会很长；
     * 2.线程池构造方法如下：
     *                  0, Integer.MAX_VALUE,
     *                  60L, TimeUnit.SECONDS,
     *                  new SynchronousQueue<Runnable>()
     *   该线程池的核心线程数为0，queue的长度为1(没有设置队列长度，则长度为1)，则说明该线程所有任务执行完成后，线程池自动关闭
     */
    ExecutorService executorService = Executors.newCachedThreadPool();
    System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

    executorService.execute(() -> System.out.println("============"));

    IntStream.rangeClosed(1, 100).boxed().forEach(i ->
        executorService.execute(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(10);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName() + " [" + i + " ]");
            }));
    TimeUnit.SECONDS.sleep(1);

    System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
  }
}
