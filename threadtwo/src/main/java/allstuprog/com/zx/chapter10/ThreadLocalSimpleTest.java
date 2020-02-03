package allstuprog.com.zx.chapter10;

public class ThreadLocalSimpleTest {
  private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
    @Override
    protected String initialValue() {
      return "zhangxuan";
    }
  };

  public static void main(String[] args) throws InterruptedException {
//    threadLocal.set("zhangxuan");
    Thread.sleep(1000);
    System.out.println(threadLocal.get());
  }

}
