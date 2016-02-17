package ericminio;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get {

    public static String contentOf(String uri) throws Exception {
        URL url = new URL( uri );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        byte[] response = new byte[ inputStream.available() ];
        inputStream.read(response);

        return new String(response);
    }
}
