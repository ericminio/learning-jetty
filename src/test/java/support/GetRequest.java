package support;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetRequest {

    public static HttpResponse get(String url) throws Exception {
        return get(url, new HashMap<String, String>());
    }

    public static HttpResponse get(String url, Map<String, String> headers) throws Exception {
        HttpURLConnection request = (HttpURLConnection) new URL( url ).openConnection();
        for (String header: headers.keySet()) {
            request.setRequestProperty( header, headers.get(header));
        }
        HttpResponse response = new HttpResponse();
        response.setStatusCode(request.getResponseCode());
        response.setContentType(request.getContentType());
        if (request.getResponseCode() < 400) {
            response.setBody(new Stringify().inputStream(request.getInputStream()));
        } else {
            response.setBody(new Stringify().inputStream(request.getErrorStream()));
        }

        return response;
    }
}
