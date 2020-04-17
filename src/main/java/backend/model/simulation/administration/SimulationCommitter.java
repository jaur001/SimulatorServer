package backend.model.simulation.administration;

import backend.model.event.EventGenerator;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

public class SimulationCommitter extends EventGenerator {


    public void commitAddProvider(ComplexCompany company, Provider provider){
        company.addProvider(provider);
        provider.addClient(company);
    }

    public void commitRemoveProvider(ComplexCompany company, Provider provider){
        if(company.getProviders().contains(provider)){
            company.removeProvider(provider);
            provider.removeClient(company);
        }
    }

    public void commitAddWorker(ComplexCompany company, Worker worker){
        company.addWorker(worker);
    }

    public void commitRemoveWorker(ComplexCompany company, Worker worker){
        company.removeWorker(worker);
    }

    public void commitRetireWorker(ComplexCompany company, Worker worker) {
        Simulation.getWorkerList().remove(worker);
        company.retireWorker(worker);
    }

    public void commitAddService(ComplexCompany company, ServiceCompany serviceCompany) {
        serviceCompany.addClient(company);
        company.addService(serviceCompany);
    }

    public void commitRemoveService(ComplexCompany company, ServiceCompany serviceCompany) {
        serviceCompany.removeClient(company);
        company.removeService(serviceCompany);
    }
}
