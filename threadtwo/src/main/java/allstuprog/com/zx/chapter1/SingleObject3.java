package allstuprog.com.zx.chapter1;

//工作中比较推荐的单例
public class SingleObject3 {

    private SingleObject3() {
    }

    private static class InstanceHolder {
        private static final SingleObject3 instance = new SingleObject3();
    }

    public SingleObject3 instance() {
        return InstanceHolder.instance;
    }
}
