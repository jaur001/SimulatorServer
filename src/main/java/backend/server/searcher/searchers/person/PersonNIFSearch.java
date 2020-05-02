package backend.server.searcher.searchers.person;

import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;

import java.util.function.Predicate;

public class PersonNIFSearch implements Search<Client> {
    @Override
    public Predicate<Client> search(String searchText) {
        return client -> (client.getNIF()+"").contains(searchText);
    }
}
