package allstuprog.com.zx.chapter6;

public class ReaderWriterClient {
    public static void main(String[] args) {
        final ShareDate shareDate = new ShareDate(10);
        new ReadWorker(shareDate).start();
        new ReadWorker(shareDate).start();
        new ReadWorker(shareDate).start();
        new ReadWorker(shareDate).start();
        new ReadWorker(shareDate).start();

        new WriterWorker(shareDate, "qwertyuiopasdfg").start();
        new WriterWorker(shareDate, "QWERTYUIOPASDFG").start();
    }
}
