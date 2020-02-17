package allstuprog.com.zx.juc.utils.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @ClassName ForkJoinRecursiveAction
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/16 19:03
 * @Version 1.0
 */
public class ForkJoinRecursiveAction {

  private final static int MAX_THRESHOLD = 3;

  private final static AtomicInteger SUM = new AtomicInteger(0);

  public static void main(String[] args) throws InterruptedException {
    final ForkJoinPool forkJoinPool = new ForkJoinPool();
    forkJoinPool.submit(new CalculateRecursiveAction(0, 100));

    forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);

    Optional.of(SUM).ifPresent(System.out::println);

  }

  private static class CalculateRecursiveAction extends RecursiveAction {

    private final int start;

    private final int end;

    private CalculateRecursiveAction(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected void compute() {
      if (end - start <= MAX_THRESHOLD) {
        SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
      } else {
        int middle = (start + end) / 2;
        CalculateRecursiveAction leftTask = new CalculateRecursiveAction(start, middle);
        CalculateRecursiveAction rightTask = new CalculateRecursiveAction(middle + 1, end);

        leftTask.fork();
        rightTask.fork();
      }
    }
  }
}
