package allstuprog.com.zx.chapter5;

public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();

        User Beijing = new User("Baobao","Beijing", gate);
        User Guangzhou = new User("GuangLao","Guangzhou", gate);
        User Shanghai = new User("ShangLao","Shanghai", gate);

        Beijing.start();
        Guangzhou.start();
        Shanghai.start();
    }
}
