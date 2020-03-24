package allstuprog.com.zx.juc.utils.executors;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName ExecutorsExample2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/21 9:05
 * @Version 1.0
 */
public class ExecutorsExample2 {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 该线程池的核心线程数，就是你所在及其的CPU的个数
     * 该线程池是非阻塞模式的，当线程池载入所有任务后会返回给你相应数量的future，后面根据该future获取相应结果
     */
    ExecutorService executorService = Executors.newWorkStealingPool();
    List<Callable<String>> callableList = IntStream.rangeClosed(1, 20).boxed().map(i ->
        (Callable<String>) () -> {
          System.out.println("Thread " + Thread.currentThread().getName());
          sleep(100);
          return "Task-" + i;
        }
    ).collect(toList());

    //线程池会返回一个存放future的List，逻辑或继续向下执行
    List<Future<String>> futures = executorService.invokeAll(callableList);

    /********
     逻辑继续向下执行
     *********/

    futures.stream().map(future->{
      try {
        //调用该方法时，程序会被block
        return future.get();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }).forEach(System.out::println);
  }

  private static void sleep(long seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
