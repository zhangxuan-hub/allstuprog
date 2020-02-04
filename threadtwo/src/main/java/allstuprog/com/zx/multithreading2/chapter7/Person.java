package allstuprog.com.zx.multithreading2.chapter7;

//类前加final，该类在任何情况下均不能被继承
final public class Person {
  private final String name;
  private final String address;


  public Person(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}
