package backend.server.searcher;

import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Search<Filterable> {

    static List<Client> searchClients(Predicate<Client> filter){
        return Simulation.getClientListCopy().stream()
                .filter(client -> !SimulationFollowAdministrator.getFollowedSimulables().contains(client))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    static List<Worker> searchWorkers(Predicate<Client> filter){
        return Simulation.getWorkerListCopy().stream()
                .filter(worker -> !SimulationFollowAdministrator.getFollowedSimulables().contains(worker))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    static List<Company> searchCompanies(Predicate<Company> filter){
        return Simulation.getCompanyListCopy().stream()
                .filter(company -> !SimulationFollowAdministrator.getFollowedSimulables().contains(company))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    static List<Restaurant> searchRestaurants(Predicate<Company> filter){
        return Simulation.getRestaurantListCopy().stream()
                .filter(company -> !SimulationFollowAdministrator.getFollowedSimulables().contains(company))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    static List<Provider> searchProviders(Predicate<Company> filter){
        return Simulation.getProviderListCopy().stream()
                .filter(company -> !SimulationFollowAdministrator.getFollowedSimulables().contains(company))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    static List<ServiceCompany> searchServiceCompanies(Predicate<Company> filter){
        return Simulation.getServiceCompanyListCopy().stream()
                .filter(company -> !SimulationFollowAdministrator.getFollowedSimulables().contains(company))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    Predicate<Filterable> search(String searchText);
}
