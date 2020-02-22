package support;

public class HttpResponse {

    private int statusCode;
    private String body;
    private String contentType;
    private byte[] binaryBody;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setBinaryBody(byte[] binaryBody) {
        this.binaryBody = binaryBody;
    }

    public byte[] getBinaryBody() {
        return binaryBody;
    }
}
