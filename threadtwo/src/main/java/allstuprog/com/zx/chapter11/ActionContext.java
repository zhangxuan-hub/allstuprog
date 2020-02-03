package allstuprog.com.zx.chapter11;

public class ActionContext {

  private ActionContext() {
  }

  private final static ThreadLocal<Context> THREAD_LOCAL = new ThreadLocal<Context>() {
    @Override
    protected Context initialValue() {
      return new Context();
    }
  };

  public final static class ContextHolder {
    private final static ActionContext instance = new ActionContext();
  }

  public static ActionContext getActionContext() {
    return ContextHolder.instance;
  }

  public Context getContext() {
    return THREAD_LOCAL.get();
  }
}
