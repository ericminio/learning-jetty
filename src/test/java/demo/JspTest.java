package demo;

import http.Resource;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import support.JettyTest;
import support.JspScratchDir;
import support.PathTo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class JspTest extends JettyTest {

    @Test
    public void canServeJsp() throws Exception {
        WebAppContext context = new WebAppContext();
        enableJspCompilation(context);
        context.setContextPath("/");
        context.setResourceBase(PathTo.resources("/webapp"));
        server.setHandler(context);
        server.start();

        assertThat(Resource.withUrl("http://localhost:8888/any.jsp"), containsString("<label>dynamic content</label>"));
    }

    private void enableJspCompilation(WebAppContext context) throws IOException {
        JettyJasperInitializer sci = new JettyJasperInitializer();
        ContainerInitializer initializer = new ContainerInitializer(sci, null);
        List<ContainerInitializer> jspInitializers = new ArrayList<ContainerInitializer>();
        jspInitializers.add(initializer);

        context.setAttribute("javax.servlet.context.tempdir", JspScratchDir.please());
        context.setAttribute("org.eclipse.jetty.containerInitializers", jspInitializers);
        context.addBean(new ServletContainerInitializersStarter(context), true);
    }
}