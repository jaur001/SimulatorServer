package backend.server.searcher.searchers.person;

import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;
import backend.server.searcher.comparators.DoubleSearchComparator;

import java.util.function.Predicate;

public class PersonSalarySearch extends DoubleSearchComparator implements Search<Client> {
    @Override
    public Predicate<Client> search(String searchText) {
        return client -> checkSearch(client.getSalary(),searchText);
    }

    @Override
    protected Double getInterval() {
        return 250.0;
    }
}
