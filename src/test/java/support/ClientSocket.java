package support;


import javax.websocket.*;

@ClientEndpoint
public class ClientSocket {

    public String received;

    @OnOpen
    public void onWebSocketConnect(Session session) {
    }

    @OnMessage
    public void onWebSocketText(String message) {
        this.received = message;
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
    }
}
