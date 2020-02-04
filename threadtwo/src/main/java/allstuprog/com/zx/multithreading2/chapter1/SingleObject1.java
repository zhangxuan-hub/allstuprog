package allstuprog.com.zx.multithreading2.chapter1;

//饿汉式单例，不支持懒加载
public class SingleObject1 {
    private static final SingleObject1 instance = new SingleObject1();

    private SingleObject1() {
    }

    public SingleObject1 getInstance() {
        return instance;
    }
}
