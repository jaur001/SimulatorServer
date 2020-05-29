package backend.server.searcher.searchers.person;

import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;
import backend.server.searcher.comparators.StringSearchComparator;

import java.util.function.Predicate;

public class PersonNIFSearch extends StringSearchComparator implements Search<Client> {
    @Override
    public Predicate<Client> search(String searchText) {
        return client -> contains(client.getNIF()+"",searchText);
    }
}
