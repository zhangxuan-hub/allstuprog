package allstuprog.com.zx.juc.utils.executors;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @ClassName ExcecutorServiceExample3
 * @Author Administrator
 * @Description TODO
 * @Date 2020/3/14 11:13
 * @Version 1.0
 */
public class ExcecutorServiceExample3 {

    public static void main(String[] args) {
//        testRemove();
//        testPreStartCoreThread();
//        testPreStartAllCoreThread();
        testThreadPoolAdvice();
    }

    private static void testRemove() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        //设置核心线程超时时间
        executorService.setKeepAliveTime(20, TimeUnit.SECONDS);
        //设置allowCoreThreadTimeOut(Boolean boolean)方法后，就需要设置超时时间
        executorService.allowCoreThreadTimeOut(true);
        IntStream.rangeClosed(0, 5).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * prestartCoreThread()
     * 该方法是属于ThreadPoolExecutor类的一个方法。
     * 在没有任务时,其可以预先启动一些线程，启动的最大数量数量为最大核心线程数；
     * 该方法每执行一次启动一个线程，如果没有任务该线程处于空闲状态。
     * 如果线程启动成功，该方法会返回true，反之返回false。
     */
    private static void testPreStartCoreThread() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        System.out.println(executorService.getActiveCount());
        executorService.prestartCoreThread();
        System.out.println(executorService.getActiveCount());
        executorService.prestartCoreThread();
        System.out.println(executorService.getActiveCount());
        executorService.prestartCoreThread();
        System.out.println(executorService.getActiveCount());

    }

    /**
     * prestartAllCoreThreads()
     * 该方法是属于ThreadPoolExecutor类的一个方法。
     * 在没有任务时,其可以预先启动一些线程，启动的最大数量数量为最大核心线程数；
     * 该方法一次性可以启动的最大线程数量为核心线程的最大数量，如果没有任务这些线程处于空闲状态。
     */
    private static void testPreStartAllCoreThread() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executorService.setMaximumPoolSize(15);
        executorService.prestartAllCoreThreads();
        System.out.println(executorService.getActiveCount());

    }

    /**
     * beforeExecute: 在线程池创建线程前需要执行的操作
     * afterExecute: 当该线程结束前需要执行的操作
     */
    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("init the " + ((MyRunnable)r).getData() );
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if(null==t) {
                System.out.println("successful " + ((MyRunnable)r).getData());
            }else {
                t.printStackTrace();
            }
        }
    }

    private static void testThreadPoolAdvice() {
        ThreadPoolExecutor executorService = new MyThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r->{
            Thread thread = new Thread(r);
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(new MyRunnable(1) {
            @Override
            public void run() {
                System.out.println(1/0);
            }
        });
    }

    private abstract static class MyRunnable implements Runnable{

        private final int no;

        protected MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return this.no;
        }
    }
}
