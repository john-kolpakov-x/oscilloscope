package kz.pompei.oscilloscope.launchers;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class WebServer {
  public static void main(String[] args) throws Exception {
    new WebServer().run();
  }

  private void run() throws Exception {

    ResourceHandler resourceHandler=new ResourceHandler();
    resourceHandler.setDirectoriesListed(true);
    resourceHandler.setResourceBase("web");

    Server server = new Server(8080);

    server.setHandler(resourceHandler);

    server.start();
    server.join();
  }
}
