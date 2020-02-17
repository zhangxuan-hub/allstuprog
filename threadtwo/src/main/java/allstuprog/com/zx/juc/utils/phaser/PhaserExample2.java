package allstuprog.com.zx.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName PhaserExample2
 * @Author Administrator
 * @Description TODO
 * @Date 2020/2/16 20:21
 * @Version 1.0
 */
public class PhaserExample2 {

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  public static void main(String[] args) {

    final Phaser phaser = new Phaser(5);

    IntStream.rangeClosed(1, 6).forEach(i->new Athletes(i,phaser));

  }

  static class Athletes extends Thread {

    private final int no;

    private final Phaser phaser;

    Athletes(int no, Phaser phaser) {
      this.no = no;
      this.phaser = phaser;
      start();
    }

    @Override
    public void run() {
      try {
        System.out.println(no + ": start running... ..." );
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
        System.out.println(no + ": end run" );
        //相当于 countDownLatch.await()
        phaser.arriveAndAwaitAdvance();

        System.out.println(no + ": start bycycle... ..." );
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
        System.out.println(no + ": end bycycle." );
        phaser.arriveAndAwaitAdvance();

        System.out.println(no + ": start long jump... ..." );
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
        System.out.println(no + ": end long jump." );
        phaser.arriveAndAwaitAdvance();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
