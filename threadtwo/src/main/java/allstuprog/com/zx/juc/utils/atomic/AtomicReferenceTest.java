package allstuprog.com.zx.juc.utils.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

  public static void main(String[] args) {
    AtomicReference<Simple> atomic = new AtomicReference<Simple>(new Simple("zhangxuan", 25));
    System.out.println(atomic.get());

    boolean result = atomic.compareAndSet(new Simple("zhangsan", 15), new Simple("lisi", 18));
    System.out.println(result);


  }

  static class Simple{
    private String name;
    private int age;

    public Simple(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

    @Override
    public String toString() {
      return "Simple{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }
}
