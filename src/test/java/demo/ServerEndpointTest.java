package demo;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.junit.Before;
import org.junit.Test;
import support.ClientSocket;
import support.JettyTest;
import websocket.SocketService;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.ServerContainer;
import java.net.URI;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ServerEndpointTest extends JettyTest {

    @Before
    public void initializeServerEndpoint() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
        container.addEndpoint(SocketService.class);
        server.start();
    }

    @Test(timeout = 500)
    public void canServeServerEndpoint() throws Exception {
        URI uri = URI.create("ws://localhost:8888/products/42");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientSocket client = new ClientSocket();
        container.connectToServer(client, uri);
        while (client.received == null) { Thread.sleep(10); }

        assertThat(client.received, equalTo("42"));
    }
}
