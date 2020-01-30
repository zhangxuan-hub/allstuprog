package allstuprog.com.zx.chapter4;

public class ObserverClient {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        subject.setState(100);
        System.out.println("************");
        subject.setState(100);
        System.out.println("************");
        subject.setState(200);
    }

}
