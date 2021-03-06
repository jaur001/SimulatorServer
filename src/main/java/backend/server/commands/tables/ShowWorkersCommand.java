package backend.server.commands.tables;

import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowWorkersCommand extends PageableFrontCommand<Worker> {
    @Override
    public void process() {
        checkPagination();
        forward("/workers.jsp");
    }

    @Override
    protected String getName() {
        return "workerList";
    }

    @Override
    protected List<Worker> loadList() {
        return Simulation.getWorkerListCopy();
    }

}
