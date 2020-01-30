package allstuprog.com.zx.chapter5;

public class Gate {
    private int counter = 0;
    private String name = "NoName";
    private String address = "NoWhere";

    public synchronized void pass(String name, String address) {
        counter++;
        this.name = name;
        this.address = address;

        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("****BROKEN****" + toString());
        }
    }

    public String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
