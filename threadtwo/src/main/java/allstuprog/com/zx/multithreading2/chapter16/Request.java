package allstuprog.com.zx.multithreading2.chapter16;

public class Request {

  private final String name;

  private final int number;


  public Request(final String name, final int number) {
    this.name = name;
    this.number = number;
  }

  @Override
  public String toString() {
    return "Request => No. " + number + " Name. " + name;
  }

  public void execute() {
    System.out.println(Thread.currentThread().getName() + " executed " + this);
  }
}
