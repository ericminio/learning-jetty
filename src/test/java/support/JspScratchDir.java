package support;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class JspScratchDir {

    @Test
    public void expectedScratchDirectory() throws IOException {
        assertThat(JspScratchDir.please().getAbsolutePath(), endsWith("embedded-jetty-jsp"));
    }

    public static File please() throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");

        if (!scratchDir.exists())
        {
            if (!scratchDir.mkdirs())
            {
                throw new IOException("Unable to create scratch directory: " + scratchDir);
            }
        }
        return scratchDir;
    }
}
