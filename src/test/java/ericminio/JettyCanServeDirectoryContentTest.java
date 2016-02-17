package ericminio;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class JettyCanServeDirectoryContentTest {

    private Server server;

    @Before
    public void startServer() throws Exception {
        server = new Server(8888);
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    @Test
    public void canServeDirectoryContent() throws Exception {
        ResourceHandler files = new ResourceHandler();
        files.setResourceBase("./src");
        files.setDirectoriesListed(true);
        server.setHandler(files);
        server.start();

        assertThat(Get.contentOf("http://localhost:8888"), containsString("test"));
    }
}
