package demo;

import jaxrs.GreetingsApplication;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.Test;
import support.HttpResponse;
import support.JettyTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static support.GetRequest.get;

public class JaxRsTest extends JettyTest {

    @Test
    public void exploration() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletContainer jersey = new ServletContainer(ResourceConfig.forApplicationClass(GreetingsApplication.class));
        context.addServlet(new ServletHolder(jersey), "/*");

        server.setHandler(context);
        server.start();

        HttpResponse response = get("http://localhost:8888/api/greetings");

        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.getBody(), equalTo("hello world"));
    }
}
