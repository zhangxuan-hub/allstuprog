package allstuprog.com.zx.juc.utils.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ExecutorBuild
 * @Author Administrator
 * @Description 构造方法说明
 * @Date 2020/2/17 17:57
 * @Version 1.0
 */
public class ExecutorBuild {

  public static void main(String[] args) {

  }

  /**
   * int corePoolSize: 线程池核心工作线程数,即：即使线程池是空闲状态也会保持这个数量的线程， 除非对allowCoreThreadTimeOut进行设置 int
   * maximumPoolSize: 线程池最大线程数，即：线程池能够开辟的线程的最大个数 long keepAliveTime: 如果开启的线程数量超过corePoolSize，且均为空闲，则当超过keepAliveTime后，
   * 线程池会将超过corePoolSize数量的线程进行回收 TimeUnit unit: 对于keepAliveTime设置的时间单元 BlockingQueue<Runnable>
   * workQueue: 需要线程执行的任务，会先放到这个queue中，通过调用{@execute}进行执行
   * ThreadFactory: 线程工厂。线程池按照该线程创建线程。
   * RejectedExecutionHandler: 线程池拒绝策略。当线程池的执行线程数达到最大或workQueue数量达到最大时使用
   */
  private static void buildThreadPoolExecutor() {
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 30,
        TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), r->{
      Thread t = new Thread(r);
      return t;
    });
  }
}