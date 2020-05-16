package backend.model.simulation.administration;

import backend.model.event.EventGenerator;
import backend.model.event.events.client.DeadClientEvent;
import backend.model.event.events.company.ClosedCompanyEvent;
import backend.model.event.events.company.changes.*;
import backend.model.event.events.company.monthlyCompany.ClientAddedProviderEvent;
import backend.model.event.events.company.monthlyCompany.ClientAddedServiceEvent;
import backend.model.event.events.company.monthlyCompany.ClientRemovedProviderEvent;
import backend.model.event.events.company.monthlyCompany.ClientRemovedServiceEvent;
import backend.model.event.events.worker.FiredWorkerEvent;
import backend.model.event.events.worker.HiredWorkerEvent;
import backend.model.event.events.worker.RetiredWorkerEvent;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompanyWithStaff.ComplexWorkerWithStaff;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.MonthlyCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;

public class SimulationCommitter extends EventGenerator {


    public void commitAddProvider(ComplexCompany company, Provider provider){
        company.addProvider(provider);
        provider.addClient(company);
        addEvent(new NewProviderCompanyEvent(company,provider));
        addEvent(new ClientAddedProviderEvent(provider,company));
    }

    public void commitRemoveProvider(ComplexCompany company, Provider provider){
        if(company.getProviders().contains(provider)){
            company.removeProvider(provider);
            provider.removeClient(company);
            addEvent(new RemovedProviderCompanyEvent(company,provider));
            addEvent(new ClientRemovedProviderEvent(provider,company));
        }
    }

    public void commitAddWorker(ComplexWorkerWithStaff company, Worker worker){
        company.addWorker(worker);
        addEvent(new WorkerHiredCompanyEvent(company,worker));
        addEvent(new HiredWorkerEvent(worker,company));
    }

    public void commitRemoveWorker(ComplexWorkerWithStaff company, Worker worker){
        company.removeWorker(worker);
        addEvent(new WorkerFiredCompanyEvent(company,worker));
        addEvent(new FiredWorkerEvent(worker,company));
    }

    public void commitRetireWorker(ComplexWorkerWithStaff company, Worker worker) {
        Simulation.getWorkerList().remove(worker);
        company.retireWorker(worker);
        addEvent(new WorkerRetiredCompanyEvent(company,worker));
        addEvent(new RetiredWorkerEvent(worker,company));
    }

    public void commitAddService(ComplexCompany company, ServiceCompany serviceCompany) {
        serviceCompany.addClient(company);
        company.addService(serviceCompany);
        addEvent(new NewServiceCompanyEvent(company,serviceCompany));
        addEvent(new ClientAddedServiceEvent(serviceCompany,company));
    }

    public void commitRemoveService(ComplexCompany company, ServiceCompany serviceCompany) {
        if(company.getServices().contains(serviceCompany)){
            serviceCompany.removeClient(company);
            company.removeService(serviceCompany);
            addEvent(new RemovedServiceCompanyEvent(company,serviceCompany));
            addEvent(new ClientRemovedServiceEvent(serviceCompany,company));
        }
    }

    public void commitDiePerson(Client client) {
        if(client instanceof Worker) Simulation.getWorkerList().remove(client);
        Simulation.getClientList().remove(client);
        addEvent(new DeadClientEvent(client));
    }

    public void commitCloseCompany(Company company) {
        if(company instanceof ComplexCompany) commitCloseComplexCompany((ComplexCompany)company);
        Simulation.getCompanyList().remove(company);
        addEvent(new ClosedCompanyEvent(company));
    }

    public void commitCloseComplexCompany(ComplexCompany company) {
        removeProviders(company);
        if(company instanceof ComplexWorkerWithStaff)removeWorkers((ComplexWorkerWithStaff)company);
        removeServices(company);
        if(company instanceof MonthlyCompany) removeCompanyClients(company);
    }



    private void removeServices(ComplexCompany company) {
        company.getServices()
                .forEach(serviceCompany -> serviceCompany.removeClient(company));
    }

    private void removeProviders(ComplexCompany company) {
        company.getProviders()
                .forEach(provider -> provider.removeClient(company));
    }

    private void removeWorkers(ComplexWorkerWithStaff company) {
        company.getWorkers()
                .forEach(worker -> commitRemoveWorker(company,worker));
    }

    private void removeCompanyClients(ComplexCompany company) {
        if(company instanceof Provider) removeProviderClients((Provider) company);
        else if(company instanceof ServiceCompany) removeServiceClients((ServiceCompany) company);
    }

    private void removeProviderClients(Provider provider) {
        provider.getCompanyList()
                .forEach(company -> commitRemoveProvider(company,provider));
    }

    private void removeServiceClients(ServiceCompany serviceCompany) {
        serviceCompany.getCompanyList()
                .forEach(company -> commitRemoveService(company,serviceCompany));
    }
}
