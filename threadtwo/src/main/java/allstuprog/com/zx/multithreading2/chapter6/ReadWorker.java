package allstuprog.com.zx.multithreading2.chapter6;

public class ReadWorker extends Thread {

    private final ShareDate shareDate;

    public ReadWorker(ShareDate shareDate) {
        this.shareDate = shareDate;
    }

    @Override
    public void run() {
        try{
            while(true) {
                char[] readBuf = shareDate.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
