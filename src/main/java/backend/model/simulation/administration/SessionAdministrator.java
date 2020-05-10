package backend.model.simulation.administration;

import backend.server.EJB.SessionDataStatefulBean;
import backend.server.EJB.SessionsListSingletonBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SessionAdministrator {

    private static SessionsListSingletonBean sessionList;

    static {
        try {
            sessionList = InitialContext.doLookup("java:global/RestaurantSimulator_war_exploded/SessionsListSingletonEJB");
        } catch (NamingException e) {
            sessionList = new SessionsListSingletonBean();
        }
    }

    public static SessionsListSingletonBean getSessionList(){
        return sessionList;
    }

    public static SessionDataStatefulBean getSessionData(Integer id){
        return sessionList.getSessionData(id);
    }

    public static SessionDataStatefulBean getSessionData(){
        return sessionList.getSessionData(1);
    }
}
