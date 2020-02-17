package allstuprog.com.zx.juc.utils.atomic;

public class JITTest {

  private static boolean init = false;

  public static void main(String[] args) throws InterruptedException {
    new Thread(){
      @Override
      public void run() {
        while(!init) {
          System.out.println(".");
        }
      }
    }.start();

    Thread.sleep(1_000);

    new Thread() {
      @Override
      public void run() {
        init = true;
        System.out.println("set init to true");
      }
    }.start();
  }
}
