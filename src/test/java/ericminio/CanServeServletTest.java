package ericminio;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class CanServeServletTest {

    private Server server;

    @Before
    public void aJettyServer() throws Exception {
        server = new Server(8888);
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    @Test
    public void canProvideOneSessionToServlets() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/hello");
        context.addServlet(new ServletHolder(new HelloServlet()), "/world");
        server.setHandler(context);
        server.start();

        assertThat(Get.contentOf("http://localhost:8888/hello/world"), containsString("session available"));
    }

    public class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            String availability = request.getSession() != null ? "available" : "ko";

            response.getWriter().println("session " + availability);
        }
    }
}
