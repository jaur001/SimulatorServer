package backend.server.EJB;

import backend.model.simulation.administration.data.SimulationDataController;
import backend.model.simulation.administration.initializer.SimulationInitializerController;
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
        if (SimulationDataController.getSimulationData().getExecuting().get()) {
            SimulationDataController.getSimulationData().getTimeLine().play();
            SimulationDataController.getSimulationData().getSimulableController().manageSimulation();
        }
        if (!SimulationDataController.getSimulationData().getRestart().get()) {
            timerService.createSingleActionTimer(TimeLine.getSpeed(), new TimerConfig());
        } else SimulationInitializerController.reset();
    }
}
