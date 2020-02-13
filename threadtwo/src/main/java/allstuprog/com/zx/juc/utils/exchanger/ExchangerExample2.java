package allstuprog.com.zx.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @ClassName ExchangerExample2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/13 20:31
 * @Version 1.0
 */
public class ExchangerExample2 {

  private final static Exchanger<Object> EXCHANGER = new Exchanger<Object>();

  public static void main(String[] args) {
    new Thread(()->{
      Object obj = new Object();
      System.out.println("A will send the object " + obj);
      try {
        Object resObj = EXCHANGER.exchange(obj);
        System.out.println("A receive the object " + resObj);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(()->{
      Object obj = new Object();
      System.out.println("B will send the object " + obj);
      try {
        Object resObj = EXCHANGER.exchange(obj);
        System.out.println("B receive the object " + resObj);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
