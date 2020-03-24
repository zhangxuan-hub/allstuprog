package allstuprog.com.zx.juc.utils.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName ExecutorServiceExample2
 * @Author 拒绝策略详解 RejectedExecution
 * 拒绝策略有四大类，分别是：AbortPolicy、DiscardPolicy、CallerRunsPolicy、DiscardOldestPolicy
 * @Description TODO
 * @Date 2020/3/14 9:07
 * @Version 1.0
 */
public class ExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException {
//        testRejectedExecution();
//        testDiscardPolicy();
//        testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }

    /**
     * AbortPolicy
     * 触发条件：1.如果线程的数量超过核心线程数+可执行最大线程数+(缓存村队列的最大长度-1)时，直接抛出异常，不做任何处理；
     *         2.但线程池会将正在执行的任务执行完成，调用shutdown方法后再关闭，不会立刻关闭；
     * 如下为例：当提交到线程池的数量超过3时，线程池会抛出异常
     *
     * @throws java.util.concurrent.RejectedExecutionException
     */
    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.rangeClosed(1, 4).forEach(i ->
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
    }

    /**
     * DiscardPolicy(强烈不建议使用)
     * 触发条件: 与AbortPolicy相同，但该策略被触发后，会直接丢弃触发拒绝策略的任务，不会抛出任何异常，故强烈不建议使用。
     */
    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardPolicy());

        IntStream.rangeClosed(1, 3).boxed().forEach(i->{
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        });
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(()-> System.out.println("x"));
        System.out.println("============");
    }

    /**
     * CallerRunsPolicy
     * 触发条件: 暂不明白
     */
    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        IntStream.rangeClosed(1, 4).forEach(i ->
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );

        TimeUnit.SECONDS.sleep(1);
//        executorService.execute(() -> {
//            System.out.println("x");
//            System.out.println(Thread.currentThread().getName());
//        });
        System.out.println("============");
        executorService.shutdown();
    }

    /**
     * DiscardOldestPolicy
     * 当有新任务提交时，如果缓存队列中有等待执行的任务，则会将缓存队列中的任务替换掉，执行新提交的任务。
     * 在缓存中替换任务的个数就是新提交任务的个数，假如新提交了2个任务，则会将缓存任务中的两个任务替换掉。
     */
    private static void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardOldestPolicy());

        IntStream.rangeClosed(1, 10).forEach(i ->
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("I come from lamda!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
        executorService.execute(() -> {
            System.out.println("y");
            System.out.println(Thread.currentThread().getName());
        });
        executorService.execute(() -> {
            System.out.println("z");
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("============");
        executorService.shutdown();
    }
}
