package allstuprog.com.zx.multithreading2.chapter4;

public class OctalObserver extends  Observer {

    protected OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("" + Integer.toOctalString(subject.getState()));
    }
}
