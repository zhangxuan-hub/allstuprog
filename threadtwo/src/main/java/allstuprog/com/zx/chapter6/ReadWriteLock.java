package allstuprog.com.zx.chapter6;

public class ReadWriteLock {
    //当前正在进行读操作的线程数
    private int readingReaders = 0;
    //当前正在等待执行读操作的线程数
    private int waitingReaders = 0;
    //当前正在执行写操作的线程数
    //该值肯定等于1，因为同一时刻只能有一个线程执行写操作
    private int writingWriter = 0;
    //当前正在等待执行写操作的线程数
    private int waitingWriters = 0;
    //如果写操作优先，则将其置为true
    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    //读锁
    public synchronized void readLock() throws InterruptedException {
        //先加入等待读取队列
        this.waitingReaders++;
        try {
            //如果有线程正在执行写操作、perferWriter=true或者有正在等待执行写操作的线程，则等待执行读操作的线程等待
            while (writingWriter > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait();
            }
            //当可以执行读操作时，让正在执行读操作计数器加1
            readingReaders++;
        } finally {
            //当可以执行读操作时，让等待执行读操作计数器减1
            waitingReaders--;
        }
    }

    //读取完成后释放锁
    public synchronized void readUnlock() {
        this.readingReaders--;
        this.notifyAll();
    }

    //写锁
    public synchronized void wirteLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (this.readingReaders > 0 || this.writingWriter > 0) {
                this.wait();
            }
            this.writingWriter++;
        } finally {
            this.waitingWriters--;
        }
    }

    //写操作完成后释放锁
    public synchronized void writeUnlock() {
        this.writingWriter--;
        this.notifyAll();
    }
}
