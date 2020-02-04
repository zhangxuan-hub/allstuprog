package allstuprog.com.zx.multithreading2.chapter15;

public class AppServer {

  private int port;

  private final static int DEFAULT_PORT = 12722;

  public AppServer() {
    this(DEFAULT_PORT);
  }

  public AppServer(int port) {
    this.port = port;
  }
}
