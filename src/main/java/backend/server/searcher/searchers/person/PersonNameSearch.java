package backend.server.searcher.searchers.person;

import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;

import java.util.function.Predicate;

public class PersonNameSearch implements Search<Client> {
    @Override
    public Predicate<Client> search(String searchText) {
        return client -> (client.getName().toLowerCase()).contains(searchText.toLowerCase());
    }
}
