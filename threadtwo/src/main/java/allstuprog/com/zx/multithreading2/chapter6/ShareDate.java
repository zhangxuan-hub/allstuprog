package allstuprog.com.zx.multithreading2.chapter6;

//共享数据
public class ShareDate {
    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareDate(int size) {
        buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        }finally {
            lock.readUnlock();
        }
    }

    public void wirte(char data) throws InterruptedException {
        try{
            lock.wirteLock();
            this.doWrite(data);
        }finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char data) {
        for(int i=0; i<buffer.length; i++) {
            buffer[i] = data;
            slowly(10);
        }
    }

    private char[] doRead() {
        char[] newCBuf = new char[buffer.length];
        for(int i=0; i<buffer.length; i++)
            newCBuf[i] = buffer[i];
        slowly(50);
        return newCBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
