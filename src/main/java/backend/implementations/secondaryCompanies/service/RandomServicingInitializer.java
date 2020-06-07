package backend.implementations.secondaryCompanies.service;

import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.utils.MathUtils;
import backend.view.loaders.secondaryCompanies.service.ServicingInitializer;

import java.util.List;

public class RandomServicingInitializer implements ServicingInitializer {
    @Override
    public ServiceCompany provide(Service service) {
        List<ServiceCompany> companiesWithService = Simulation.getServiceCompanyList(service);
        if(companiesWithService.size()==0) return null;
        return companiesWithService.get(MathUtils.random(0,companiesWithService.size()));
    }
}
