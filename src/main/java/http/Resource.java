package http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Resource {

    public static String withUrl(String uri) throws Exception {
        URL url = new URL( uri );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        byte[] response = new byte[ inputStream.available() ];
        inputStream.read(response);

        return new String(response);
    }
}
