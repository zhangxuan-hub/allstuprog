package allstuprog.com.zx.juc.utils.atomic;

public class AtomicBooleanFlag {

  private static boolean flag = true;

  public static void main(String[] args) throws InterruptedException {
    new Thread(()->{
      while(flag) {
        try {
          Thread.sleep(1_000);
//          System.out.println("I am working.");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println("I am finished");
    }).start();

    Thread.sleep(5000);

    new Thread(()->{
      flag = false;
    }).start();
  }

}
