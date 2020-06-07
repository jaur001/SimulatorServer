package backend.view.loaders.secondaryCompanies.service;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.ServiceCompany;

public interface ServicingInitializer {
    ServiceCompany provide(Service service);
}
