package backend.server.sockets;
import backend.model.event.EventController;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/eventSocketEndpoint")
public class EventWebSocket {

    private static final String ERROR = "";

    @OnOpen
    public void onOpen(){
        System.out.println("Event socket opened....");
    }

    @OnClose
    public void onClose(){
        System.out.println("Event socket close....");
    }

    @OnMessage
    public String onMessage(String message){
        return EventController.getPendingEvents();
    }

    @OnError
    public void onError(Throwable e){
        System.out.print(ERROR);
    }
}
