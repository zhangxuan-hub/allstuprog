package allstuprog.com.zx.chapter13;

import java.util.stream.IntStream;

public class ProductAndConsumerClient {

  public static void main(String[] args) {
    final MessageQueue messageQueue = new MessageQueue();

    IntStream.range(0, 5).forEach(
        i->new ProducterThread(messageQueue, i).start()
    );

    IntStream.range(0,1).forEach(
        i->new CustomerThread(messageQueue, i).start()
    );
  }
}
