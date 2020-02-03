package allstuprog.com.zx.chapter11;

public class ExecutionTask implements Runnable {

  private QueryFormDBAction queryAction;

  @Override
  public void run() {
    final Context context = new Context();
    queryAction.execute(context);
  }
}
