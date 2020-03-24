package allstuprog.com.zx.juc.utils.executors;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName ExecutorServiceExample4
 * @Author Administrator
 * @Description TODO
 * @Date 2020/3/23 9:09
 * @Version 1.0
 */
public class ExecutorServiceExample4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testInvokeAny();
//        testInvokeAll();
        testSubmitRunnable();
    }

    /**
     * {@link ExecutorService#invokeAny(Collection)}
     * <p>
     *
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException     invokeAny: 该类中可以传入一个Collection<? extends Callable<T>>类型的集合，
     *                              其中Callable<T>是一个functionInerface,它包含一个方法call(),该方法可以将结果返回。
     *                              当invokeAny被执行时，它会将Collection集合中第一个线程执行完的结果返回，同时Collection集合中的其他线程不会被执行
     *                              该方法可以设置返回值等待时间
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> collect = IntStream.rangeClosed(1, 4).boxed().map(
                i -> (Callable<Integer>) () -> {
                    /**
                     * ThreadLocalRandom是与线程池绑定的，当多个线程同时访问时不存在线程安全的问题
                     * ThreadLocalRandom.current().nextInt(20)表示线程休眠1-20s时间的任意时间
                     */
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    return i;
                }
        ).collect(toList());
        /**
         * 设置返回值等待时间
         * 如果超时，则不再等待返回值
         **/
//        Integer value = executorService.invokeAny(collect, 1, TimeUnit.SECONDS);
        Integer value = executorService.invokeAny(collect);
        System.out.println("========finish========");
        System.out.println("value: " + value);

    }

    /**
     * {@link ExecutorService#invokeAny(Collection, long, TimeUnit)}
     * <p>
     *
     * @throws InterruptedException
     * @throws ExecutionException   invokeAll: 等待Collect中所有线程执行完成后，统一返回执行结果
     *                              该方法可以设置等待结果时间，如果超时后还没有结果返回则不再等待
     **/
    private static void testInvokeAll() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        /**
         * 1.生成一个有1-5整数，包含Callable接口的任务集合
         **/
        Collection<Callable<Integer>> collect = IntStream.rangeClosed(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    try {
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                        return i;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).collect(toList());
        /**
         * 2.通过线程池的invokeAll方法执行集合中的任务，并将每个线程的返回值放到List<Future<Integer>>中
         **/
        List<Future<Integer>> futures = executorService.invokeAll(collect);
        /**
         * 3.输出执行结果
         **/
        Stream<Integer> stream = futures.stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        });
        stream.forEach(System.out::println);
    }

    /**
     * {@link ExecutorService#submit(Runnable, Object)}
     * submit(): 可以通过该方法在线程池执行之前设置一个值(这个值是任意类型的)
     *           当通过该方法启动线程池执行任务，任务执行全部执行完成后，该方法会返回之前设定的值。
     *           这个值不是线程执行完成后的值。
     *           如果想获取线程线程执行完成后的返回值，可以用Callable()接口
     **/
    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "RESULT");
        String res = future.get();
        System.out.println("R: " + res);
        executorService.shutdownNow();

    }
}
