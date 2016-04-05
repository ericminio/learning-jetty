package support;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class PathTo {

    @Test
    public void expectedJspUri() throws Exception {
        assertThat(PathTo.resources("/webapp"), endsWith("/webapp"));
    }

    public static String resources(String type) throws URISyntaxException {
        return Thread.currentThread().getClass().getResource(type).toURI().toASCIIString();
    }
}
