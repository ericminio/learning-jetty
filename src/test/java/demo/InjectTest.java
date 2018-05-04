package demo;

import http.PingServlet;
import http.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import support.JettyTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class InjectTest extends JettyTest {

    @Test
    public void canBeUsedToInjectSingletonBean() throws Exception {
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase("src/main/resources");
        server.setHandler(context);
        context.addServlet(PingServlet.class, "/ping");
        context.addEventListener(new org.jboss.weld.environment.servlet.Listener());
        server.start();

        assertThat(Resource.withUrl("http://localhost:8888/ping"), containsString("pong"));
    }
}