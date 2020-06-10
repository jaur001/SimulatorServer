package backend.server.sockets;
import backend.model.event.EventController;
import backend.model.simulation.administration.centralControl.Simulation;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/simulableCounterSocketEndpoint")
public class SimulableCounterWebSocket {

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
        return Simulation.getClientSize() + " "
                + Simulation.getRestaurantSize() + " "
                + Simulation.getProviderSize() + " "
                + Simulation.getServiceCompanySize() + " "
                + Simulation.getWorkerSize() + " ";
    }

    @OnError
    public void onError(Throwable e){
        System.out.print(ERROR);
    }
}
