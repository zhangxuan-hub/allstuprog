package allstuprog.com.zx.juc.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CyclicBarrierExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/12 19:57
 * @Version 1.0
 */
public class CyclicBarrierExample1 {

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    //parties:需要相互之间等待的线程数，如果该数字大于真实线程数，程序会block住
    //Runnable接口：当所有线程cyclicBarrier.await()这个约定点之后，会调用改接口中的逻辑
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
      @Override
      public void run() {
        System.out.println("all of finished.");
      }
    });

    /**
     * 有T1和T2两个线程，只有当T1和T2两个线程cyclicBarrier.await()方法前的逻辑执行完成之后，
     * 才会执行System.out.println("T1 The other thread finished too.")和System.out.println("T2 The other thread finished too.");
     */
    new Thread(()->{
      try {
        TimeUnit.SECONDS.sleep(20);
        System.out.println("T1 finished");
        cyclicBarrier.await();
        System.out.println("T1 The other thread finished too.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(()->{
      try {
        TimeUnit.SECONDS.sleep(10);
        System.out.println("T2 finished.");
        cyclicBarrier.await();
        System.out.println("T2 The other thread finished too.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }).start();

    while(true) {
      //当前处于等待的线程数
      System.out.println("NumberWaiting: " + cyclicBarrier.getNumberWaiting());
      //线程分割数
      System.out.println("Parities: " + cyclicBarrier.getParties());
      //是否有被Broken的线程
      System.out.println("isBroken: " + cyclicBarrier.isBroken());
      System.out.println("********");
      TimeUnit.SECONDS.sleep(2);
    }
  }
}
