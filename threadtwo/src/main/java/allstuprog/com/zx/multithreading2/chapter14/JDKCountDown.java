package allstuprog.com.zx.multithreading2.chapter14;

import java.util.Random;
import java.util.stream.IntStream;

public class JDKCountDown {

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  public static void main(String[] args) throws InterruptedException {

    System.out.println("准备多线程处理任务");
    //1.第一阶段
    //被等待的线程数量为5
    final CountDown latch = new CountDown(5);
    IntStream.rangeClosed(1, 5).forEach(i -> {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + " is working. ");
        try {
          Thread.sleep(RANDOM.nextInt(1000));

        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          latch.down();
        }
      }, String.valueOf(i)).start();
    });
    latch.await();
    //2.第二阶段
    System.out.println("多线程任务全部结果，准备第二阶段任务.");
    System.out.println("... ...");
    System.out.println("FINISH.");


  }

}
