package allstuprog.com.zx.multithreading2.chapter1;

//双重检测锁单例
public class SingleObject2 {

    private static volatile SingleObject2 instance = null;

    private SingleObject2() {
    }

    public  SingleObject2 getInstance() {
        if (null == instance){
            synchronized (SingleObject2.class) {
                instance = new SingleObject2();
            }
        }
        return SingleObject2.instance;
    }
}
