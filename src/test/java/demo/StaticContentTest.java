package demo;

import org.eclipse.jetty.server.handler.ResourceHandler;
import org.junit.Test;
import http.Resource;
import support.JettyTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class StaticContentTest extends JettyTest {

    @Test
    public void canServeDirectoryContent() throws Exception {
        ResourceHandler files = new ResourceHandler();
        files.setResourceBase("./src");
        files.setDirectoriesListed(true);
        server.setHandler(files);
        server.start();

        assertThat(Resource.withUrl("http://localhost:8888"), containsString("test"));
    }

    @Test
    public void canServeStaticFile() throws Exception {
        ResourceHandler files = new ResourceHandler();
        files.setResourceBase("./src/test/resources");
        server.setHandler(files);
        server.start();

        assertThat(Resource.withUrl("http://localhost:8888/keep.calm"), containsString("and write tests"));
    }
}
