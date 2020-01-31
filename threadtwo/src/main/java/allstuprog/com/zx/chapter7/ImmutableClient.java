package allstuprog.com.zx.chapter7;


import java.util.stream.IntStream;

public class ImmutableClient {

  public static void main(String[] args) {
    //共享数据
    Person person = new Person("zhangxuan", "xian");

    IntStream.range(0, 5).forEach(i ->
        new UserPersonThread(person).start()
    );
  }
}
