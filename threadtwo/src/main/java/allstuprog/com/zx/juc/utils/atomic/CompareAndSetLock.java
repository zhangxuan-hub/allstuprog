package allstuprog.com.zx.juc.utils.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

  //定义一个原子型整型变量，并赋初值为0
  private final AtomicInteger value = new AtomicInteger(0);
  private Thread thread;

  //获取锁方法
  public void tryLock() throws GetLockException {
    //通过compareAndSet()方法，将compareAndSet()方法内的value值更新为1，如果成功则返回true
    //返回true，说明已经成功抢到锁
    //当其他线程试图将compareAndSet()方法内的value值更新为1时，会犯失败，则无法获取该锁
    boolean success = value.compareAndSet(0, 1);
    if(success) {
      thread = Thread.currentThread();
    }else {
      throw new GetLockException("Get the Lock failed");
    }
  }

  //释放锁方法
  public void unLock() {
    //value.get()为0(get()方法获取的是compareAndSet()方法内的value值)，说明该锁已经被释放
    if(0==value.get()) {
      return;
    }
    //如果value.get()获取的值不为0，则说明锁还未释放，则需要将compareAndSet()方法内的value值置为0；
    if(thread==Thread.currentThread()) {
      value.compareAndSet(1, 0);
    }
  }
}
