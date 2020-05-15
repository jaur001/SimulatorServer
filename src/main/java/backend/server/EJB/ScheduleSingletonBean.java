package backend.server.EJB;

import backend.model.simulation.administration.Simulation;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton(name = "ScheduleSingletonEJB")
public class ScheduleSingletonBean {

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void scheduleTimer(){
        System.out.println("Number of Companies: " + Simulation.getCompanyListCopy().size());
        System.out.println("Number of Clients: " + Simulation.getClientSize());
        System.out.println("Number of Workers: " + Simulation.getWorkerSize());
    }
}
