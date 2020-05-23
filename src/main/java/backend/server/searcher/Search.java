package backend.server.searcher;

import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.administration.data.SimulationFollowAdministrator;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Search<Filterable> {

    static List<Client> searchPeople(Predicate<Client> filter){
        return Simulation.getClientListCopy().stream()
                .filter(client -> !SimulationFollowAdministrator.getFollowedSimulables().contains(client))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    static List<Company> searchCompanies(Predicate<Company> filter){
        return Simulation.getCompanyListCopy().stream()
                .filter(company -> !SimulationFollowAdministrator.getFollowedSimulables().contains(company))
                .filter(filter).collect(Collectors.toCollection(LinkedList::new));
    }

    Predicate<Filterable> search(String searchText);
}
