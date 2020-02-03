package allstuprog.com.zx.chapter8;


import java.util.function.Consumer;

public class FutureService {

  public <T> Future<T> submit(final FutureTask<T> task) {

    AsynFuture<T> asynFuture = new AsynFuture<T>();
    new Thread(() -> {
      T result = task.call();
      asynFuture.done(result);
    }).start();

    return asynFuture;
  }

  public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
    AsynFuture<T> asynFuture = new AsynFuture<T>();

    new Thread(()->{
      T result = task.call();
      asynFuture.done(result);
      consumer.accept(result);
    }).start();

    return asynFuture;
  }
}
