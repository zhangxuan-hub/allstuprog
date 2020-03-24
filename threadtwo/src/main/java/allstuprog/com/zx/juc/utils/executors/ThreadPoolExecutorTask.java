package allstuprog.com.zx.juc.utils.executors;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName ThreadPoolExecutorTask
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/18 18:58
 * @Version 1.0
 */
public class ThreadPoolExecutorTask {

  public static void main(String[] args) {
    ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(10),
        r -> {
          return new Thread(r);

        }, new ThreadPoolExecutor.AbortPolicy());

    IntStream.rangeClosed(1, 20).boxed().forEach(i ->
        executorService.execute(() -> {
          try {
            Optional.of(Thread.currentThread().getName() + " start working ......").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
            Optional.of(Thread.currentThread().getName() + " finish done.").ifPresent(System.out::println);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        })
    );

    /**
     * shutdown()方法是一个非阻塞的方法
     * 当线程池中有20个线程，其中10个线程正在工作，10个是空闲线程，则调用shutdown()方法后，10个空闲的线程会首先被打算，而其余10个正在工作的线程在工作完成后才会被打断，线程池才会关闭
     */
//    executorService.shutdown();
    //在调用awaitTermination(time, TimeUnit)方法的地方程序会阻塞住,知道线程池中所有线程的任务都执行完成或者阻塞时间达到设定时间。
//    executorService.awaitTermination(1, TimeUnit.HOURS);
    List<Runnable> runnableList = null;
    try{
      /**
       * 假设提交了20个任务，活跃线程数为10个，workqueue的长度为10个，这时会有10个任务存放在workqueue中。
       * 当调用shutdownNow()时，会返回一个List<Runable>,这个list中存放的是workqueue线程池还未执行的10个任务，同时这10个未执行的任务会从workqueue中删除。
       * 而正在执行的10个线程会立刻被打断，整个线程池结束工作
       * shutdownNow()方法是非阻塞方法。
       **/
      runnableList = executorService.shutdownNow();
      System.out.println("=========over========");
    }catch(Exception e) {
      e.printStackTrace();
    }
    System.out.println(runnableList);
    System.out.println(runnableList.size());
  }
}
