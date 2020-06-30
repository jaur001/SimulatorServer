package backend.model.simulation.administration.simulableControl;

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
import backend.model.event.events.worker.RetiredUnemployedWorkerEvent;
import backend.model.event.events.worker.RetiredWorkerEvent;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.ComplexCompanyWithStaff;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.MonthlyCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.data.SimulationDataAdministrator;

public class SimulationCommitter extends EventGenerator {


    public void commitAddProvider(ComplexCompany company, Provider provider){
        company.addProvider(provider);
        provider.addClient(company);
        addEvent(new NewProviderCompanyEvent(company,provider));
        addEvent(new ClientAddedProviderEvent(provider,company));
    }

    public void commitRemoveProvider(ComplexCompany company, Provider provider){
        if(company.getProviders().contains(provider)){
            provider.removeClient(company);
            commitRemoveProviderClient(company, provider);
        }
    }

    private void commitRemoveProviderClient(ComplexCompany company, Provider provider) {
        company.removeProvider(provider);
        addEvent(new RemovedProviderCompanyEvent(company,provider));
        addEvent(new ClientRemovedProviderEvent(provider,company));
    }

    public void commitAddWorker(ComplexCompanyWithStaff company, Worker worker){
        if(company.addWorker(worker)){
            addEvent(new WorkerHiredCompanyEvent(company,worker));
            addEvent(new HiredWorkerEvent(worker,company));
        }
    }

    public void commitRemoveWorker(ComplexCompanyWithStaff company, Worker worker){
        company.removeWorker(worker);
        addEvent(new WorkerFiredCompanyEvent(company,worker));
        addEvent(new FiredWorkerEvent(worker,company));
    }

    public void commitRetireWorker(ComplexCompanyWithStaff company, Worker worker) {
        company.retireWorker(worker);
        convertToClient(worker);
        addEvent(new RetiredWorkerEvent(worker,company));
        addEvent(new WorkerRetiredCompanyEvent(company,worker));
    }

    public void commitRetireUnemployedWorker(Worker worker){
        worker.retire();
        convertToClient(worker);
        addEvent(new RetiredUnemployedWorkerEvent(worker));
    }

    public void convertToClient(Worker worker) {
        SimulationDataAdministrator.getSimulationData().getClientList().remove(worker);
        Client client = new Client(worker.getPersonalData());
        client.setJob("Retired");
        SimulationDataAdministrator.getSimulationData().getClientList().add(client);
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
            commitRemoveServiceClient(company, serviceCompany);
        }
    }

    private void commitRemoveServiceClient(ComplexCompany company, ServiceCompany serviceCompany) {
        company.removeService(serviceCompany);
        addEvent(new RemovedServiceCompanyEvent(company,serviceCompany));
        addEvent(new ClientRemovedServiceEvent(serviceCompany,company));
    }

    public void commitDiePerson(Client client) {
        SimulationDataAdministrator.getSimulationData().getClientList().remove(client);
        addEvent(new DeadClientEvent(client));
    }

    public void commitCloseCompany(Company company) {
        if(company instanceof ComplexCompany) commitCloseComplexCompany((ComplexCompany)company);
        SimulationDataAdministrator.getSimulationData().getCompanyList().remove(company);
        addEvent(new ClosedCompanyEvent(company));
    }

    public void commitCloseComplexCompany(ComplexCompany company) {
        removeProvidersToPay(company);
        if(company instanceof ComplexCompanyWithStaff) removeWorkersToPay((ComplexCompanyWithStaff)company);
        removeServicesToPay(company);
        if(company instanceof MonthlyCompany) removeCompanyClients(company);
    }



    private void removeServicesToPay(ComplexCompany company) {
        company.getServices()
                .forEach(serviceCompany -> serviceCompany.removeClient(company));
    }

    private void removeProvidersToPay(ComplexCompany company) {
        company.getProviders()
                .forEach(provider -> provider.removeClient(company));
    }

    private void removeWorkersToPay(ComplexCompanyWithStaff company) {
        company.getWorkers()
                .forEach(Worker::fire);
    }

    private void removeCompanyClients(ComplexCompany company) {
        if(company instanceof Provider) removeProviderClients((Provider) company);
        else if(company instanceof ServiceCompany) removeServiceClients((ServiceCompany) company);
    }

    private void removeProviderClients(Provider provider) {
        provider.getCompanyClientList()
                .forEach(company -> commitRemoveProviderClient(company,provider));
    }

    private void removeServiceClients(ServiceCompany serviceCompany) {
        serviceCompany.getCompanyClientList()
                .forEach(company -> commitRemoveServiceClient(company,serviceCompany));
    }
}
