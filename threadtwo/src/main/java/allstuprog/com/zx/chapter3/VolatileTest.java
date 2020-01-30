package allstuprog.com.zx.chapter3;


public class VolatileTest {
    private static Integer init_value = 0;

    private static final Integer MAX_VALUE = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_value;
            while (init_value < MAX_VALUE) {
                System.out.printf(Thread.currentThread().getName() + ", The value update to [%d].\n", init_value++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = init_value;
            while (init_value < MAX_VALUE) {
                System.out.printf(Thread.currentThread().getName() + ", The value update to [%d].\n", init_value++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WRITER").start();
    }
}
