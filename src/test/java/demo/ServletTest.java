package demo;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;
import http.Resource;
import support.JettyTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServletTest extends JettyTest {

    @Test
    public void canProvideOneSessionToServlets() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/hello");
        context.addServlet(new ServletHolder(new HelloServlet()), "/world");
        server.setHandler(context);
        server.start();

        assertThat(Resource.withUrl("http://localhost:8888/hello/world"), containsString("session available"));
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
