package backend.server.EJB;


import backend.model.simulation.administration.data.SimulationData;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedHashMap;
import java.util.Map;

@Singleton(name = "SessionsListSingletonEJB")
public class SessionsListSingletonBean {

    private Map<Integer, SimulationData> sessionsTable;

    public SessionsListSingletonBean() {
        this.sessionsTable = new LinkedHashMap<>();
    }

    @PostConstruct
    public void init(){
        this.sessionsTable = new LinkedHashMap<>();
    }

    public int addSession(){
        SimulationData sessionData;
        try {
            sessionData = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/SessionDataStatefulEJB");
        } catch (NamingException e) {
            sessionData = new SimulationData();
        }
        Integer id = getID();
        sessionsTable.put(id,sessionData);
        return id;
    }

    private Integer getID() {
        return sessionsTable.keySet().stream()
                .reduce(1,Math::max);
    }

    public boolean isEmpty(){
        return sessionsTable.size()==0;
    }

    public SimulationData getSessionData(Integer id) {
        //System.out.println(this.getClass().getSimpleName()+ "::getSessionData(" + ")");
        return sessionsTable.get(id);
    }

    public SimulationData getSessionData(){
        return getSessionData(1);
    }

}
