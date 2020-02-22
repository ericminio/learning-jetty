package support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Bytify {

    public byte[] inputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) { return new byte[0]; }

        if (inputStream == null) { return new byte[0]; }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
