package allstuprog.com.zx.chapter11;

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
