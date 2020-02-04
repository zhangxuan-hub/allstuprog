package allstuprog.com.zx.multithreading2.chapter11;

public class QueryFormDBAction {

  public void execute(Context context) {
    try {
      Thread.sleep(1000);
      String name = "zhangxuan";
      context.setName(name);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
