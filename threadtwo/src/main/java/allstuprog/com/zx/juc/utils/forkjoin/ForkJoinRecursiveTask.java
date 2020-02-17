package allstuprog.com.zx.juc.utils.forkjoin;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @ClassName ForkJoinRecursiveTask
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/16 18:39
 * @Version 1.0
 */
public class ForkJoinRecursiveTask {

  /**
   * 每个线程处理的最大数量，超过这个数则进行拆分
   */
  private final static int MAX_THRESHOLD = 300;

  public static void main(String[] args) {

    //定义ForkJoinPool所有的forkJoin任务均在该poo中进行
    final ForkJoinPool pool = new ForkJoinPool();
    ForkJoinTask<Integer> future = pool.submit(new CalculatedRecursiveTask(1, 10000));
    try {
      //执行forkJoin任务，并得到返回值
      Integer result = future.get();
      Optional.of("result: " + result).ifPresent(System.out::println);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      //执行出错异常
      e.printStackTrace();
    }
  }

  private static class CalculatedRecursiveTask extends RecursiveTask<Integer> {

    private final int start;

    private final int end;

    private CalculatedRecursiveTask(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected Integer compute() {
      if (end - start <= MAX_THRESHOLD) {
        return IntStream.rangeClosed(start, end).sum();
      } else {
        int middle = (start + end) / 2;
        CalculatedRecursiveTask leftTask = new CalculatedRecursiveTask(start, middle);
        CalculatedRecursiveTask rightTask = new CalculatedRecursiveTask(middle + 1, end);

        leftTask.fork();
        rightTask.fork();

        return leftTask.join() + rightTask.join();
      }
    }
  }
}
