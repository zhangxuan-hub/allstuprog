package allstuprog.com.zx.chapter4;

public abstract class Observer {
    protected Subject subject;

    protected Observer(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public abstract void update();
}
