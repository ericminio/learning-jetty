package demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import http.Resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import support.JettyTest;
import support.JspScratchDir;
import support.PathTo;

public class CustomTagTest extends JettyTest {

	private WebAppContext context;

	@Before
	public void startServer() throws Exception {
		context = new WebAppContext();
        enableJspCompilation(context);
        context.setContextPath("/");
        context.setResourceBase(PathTo.resources("/webapp"));
        server.setHandler(context);
        server.start();       
	}
	
	@After
	public void closeServer() throws Exception {
		server.stop();
	}
	
	@Test
	public void canRetrieveCustomTagDefinition() throws Exception {
		assertThat(context.getResourcePaths("/WEB-INF/").size(), equalTo(1));
		assertThat(context.getResourcePaths("/WEB-INF/").iterator().next(), equalTo("/WEB-INF/hello-world.tld"));
	}
	
	@Test
    public void canServeJspWithCustomTag() throws Exception {
        assertThat(Resource.withUrl("http://localhost:8888/custom-tag.jsp"), containsString("Hello from custom tag"));
    }

    private void enableJspCompilation(WebAppContext context) throws IOException {
        JettyJasperInitializer sci = new JettyJasperInitializer();
        ContainerInitializer initializer = new ContainerInitializer(sci, null);
        List<ContainerInitializer> jspInitializers = new ArrayList<ContainerInitializer>();
        jspInitializers.add(initializer);

        context.setAttribute("javax.servlet.context.tempdir", JspScratchDir.please());
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*taglibs.*\\.jar$");
        context.setAttribute("org.eclipse.jetty.containerInitializers", jspInitializers);
        context.addBean(new ServletContainerInitializersStarter(context), true);
    }
}
