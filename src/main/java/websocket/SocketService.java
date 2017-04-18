package websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/products/{id}")
public class SocketService {

    @OnOpen
    public void onWebSocketConnect(@PathParam("id") String id, Session session) {
        session.getAsyncRemote().sendText(id);
    }

    @OnMessage
    public void onWebSocketText(String message) {
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
    }
}
