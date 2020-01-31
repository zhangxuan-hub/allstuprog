package allstuprog.com.zx.chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {

  private final int age;
  private final String name;
  private final List<String> list;


  public ImmutableTest(int age, String name) {
    this.age = age;
    this.name = name;
    this.list = new ArrayList<String>();
  }

  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  //如果想让ImmutableTest对象变是一个不可变对象，则List必须用Collections.unmodifiableList()方法进行修饰
  //如果不用Collections.unmodifiableList()进行修饰则，通过get方法获取list后，就可以修改list中的值，则该对象就不是不可变对象了
  public List<String> getList() {
    return Collections.unmodifiableList(list);
  }
}
