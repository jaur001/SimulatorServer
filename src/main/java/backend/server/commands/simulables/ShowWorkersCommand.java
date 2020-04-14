package backend.server.commands.simulables;

import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowWorkersCommand extends PageableFrontCommand<Worker> {
    @Override
    public void process() {
        checkPagination();
        forward("/workers.jsp");
    }

    @Override
    protected List<Worker> getList(int page) {
        return Simulation.getWorkerList(page);
    }

    @Override
    protected int getLimit() {
        return Simulation.getWorkerSize();
    }
}
