package backend.server.EJB;

import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.simulablesControl.SimulationInitializer;
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
        timerService.createSingleActionTimer(TimeLine.getSpeed(), new TimerConfig());
    }

    @Timeout
    public void Timeout(Timer timer){
        if (SimulationDataController.getSessionData().getExecuting().get()) {
            SimulationDataController.getSessionData().getTimeLine().play();
            SimulationDataController.getSessionData().getSimulableController().manageSimulation();
        }
        if (!SimulationDataController.getSessionData().getRestart().get()) {
            timerService.createSingleActionTimer(TimeLine.getSpeed(), new TimerConfig());
        } else SimulationInitializer.reset();
    }
}
