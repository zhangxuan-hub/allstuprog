package allstuprog.com.zx.chapter8;

public class SynInvoker {

  public static void main(String[] args) throws InterruptedException {
    FutureService service = new FutureService();
    service.submit(() -> {
      try {
        Thread.sleep(13000l);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "FINISH";
    }, System.out::println);

    System.out.println("==================");
    System.out.println(" do other thing");
    Thread.sleep(10000l);
    System.out.println("==================");

  }
}
