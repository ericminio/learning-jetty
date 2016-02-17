package support;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;

public class JettyTest {

    protected Server server;

    @Before
    public void aJettyServer() throws Exception {
        server = new Server(8888);
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }


}
