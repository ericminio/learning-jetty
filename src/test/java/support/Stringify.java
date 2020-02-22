package support;

import java.io.IOException;
import java.io.InputStream;

public class Stringify {

    public String inputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) { return ""; }

        return new String(new Bytify().inputStream(inputStream));
    }
}
