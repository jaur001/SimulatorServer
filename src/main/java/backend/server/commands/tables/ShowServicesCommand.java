package backend.server.commands.tables;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.server.servlets.PageableFrontCommand;

import java.util.List;

public class ShowServicesCommand extends PageableFrontCommand<ServiceCompany> {


    @Override
    public void process() {
        checkPagination();
        forward("/services.jsp");
    }

    @Override
    protected String getName() {
        return "serviceList";
    }

    @Override
    protected List<ServiceCompany> loadList() {
        return Simulation.getServiceCompanyListCopy();
    }

}
