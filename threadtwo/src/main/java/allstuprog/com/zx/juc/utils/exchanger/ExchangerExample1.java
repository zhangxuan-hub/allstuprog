package allstuprog.com.zx.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ExchangerExample1
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/13 19:42
 * @Version 1.0
 */
public class ExchangerExample1 {

  public static void main(String[] args) {
      final Exchanger<String> exchanger = new Exchanger<String>();

      new Thread("==A=="){
        @Override
        public void run() {
          try {
            System.out.println(Thread.currentThread().getName() + "start.");
            /**
             * ==A==线程通过exchanger.exchange()方法，将数据发送给伙伴线程==B==，并获取返回值。
             * 如果==B==线程未将返回数据准备好，则==A==线程会Block住，直到==A==线程收到==B==的返回值为止。
             * 如果想让==A==在指定时间没有接到数据，就不再等待，则可以设置超时时间
             */
            String result = exchanger.exchange("I am come from T-A.", 10, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (TimeoutException e) {
            e.printStackTrace();
          }
          System.out.println("==A== end.");
        }
      }.start();

    new Thread("==B=="){
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + "start.");
          TimeUnit.SECONDS.sleep(20);
          //将数据发送给伙伴线程==A==，并获取返回值
          String result = exchanger.exchange("I am come from T-B.");
//          System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("==B== end.");
      }
    }.start();
  }
}
