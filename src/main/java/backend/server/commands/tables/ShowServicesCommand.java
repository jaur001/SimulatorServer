package backend.server.commands.tables;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
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
    protected List<ServiceCompany> getList(int page) {
        return Simulation.getServiceCompanyList(page);
    }

    @Override
    protected int getLimit(){
        return Simulation.getServiceCompanySize();
    }

}
