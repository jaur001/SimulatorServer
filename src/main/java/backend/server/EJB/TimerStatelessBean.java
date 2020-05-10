package backend.server.EJB;

import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.Simulator;
import backend.model.simulation.timeLine.TimeLine;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

@Stateless(name = "TimerStatelessEJB")
public class TimerStatelessBean {


    @Resource
    TimerService timerService;

    @PostConstruct
    public void init(){
    }

    public void setTimer(){
        timerService.createSingleActionTimer(TimeLine.TIMEOUT, new TimerConfig());
    }

    @Timeout
    public void Timeout(Timer timer){
        if (Simulator.getSessionData().getExecuting().get()) {
            Simulator.getSessionData().getTimeLine().play();
            Simulator.getSessionData().getSimulationAdministrator().manageSimulation();
        }
        if (!Simulator.getSessionData().getRestart().get()) {
            timerService.createSingleActionTimer(TimeLine.TIMEOUT, new TimerConfig());
        } else Simulation.reset();
    }
}
