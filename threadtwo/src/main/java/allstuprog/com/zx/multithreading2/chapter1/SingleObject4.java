package allstuprog.com.zx.multithreading2.chapter1;

import java.util.stream.IntStream;

//枚举单例
public class SingleObject4 {
    private SingleObject4() {
    }

    private enum Singleton {
        Instance;

        private final SingleObject4 instance;

        Singleton() {
            instance = new SingleObject4();
        }

        public SingleObject4 getInstance() {
            return instance;
        }
    }

    public static SingleObject4 getInstance() {
        return Singleton.Instance.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(
                i->new Thread(()->{
                    System.out.println(SingleObject4.getInstance());
                }).start()
        );
    }
}
