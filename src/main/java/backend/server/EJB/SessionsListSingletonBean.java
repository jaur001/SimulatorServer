package backend.server.EJB;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedHashMap;
import java.util.Map;

@Singleton(name = "SessionsListSingletonEJB")
public class SessionsListSingletonBean {

    private Map<Integer, SessionDataStatefulBean> sessionsTable;

    public SessionsListSingletonBean() {
        this.sessionsTable = new LinkedHashMap<>();
    }

    @PostConstruct
    public void init(){
        this.sessionsTable = new LinkedHashMap<>();
    }

    public int addSession(){
        SessionDataStatefulBean sessionData;
        try {
            sessionData = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/SessionDataStatefulEJB");
        } catch (NamingException e) {
            sessionData = new SessionDataStatefulBean();
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

    public SessionDataStatefulBean getSessionData(Integer id) {
        System.out.println(this.getClass().getSimpleName()+ "::getSessionData(" + ")");
        return sessionsTable.get(id);
    }

}
