package backend.server.searcher.searchers.company;

import backend.model.simulables.company.Company;
import backend.server.searcher.Search;

import java.util.function.Predicate;

public class CompanyNameSearch implements Search<Company> {
    @Override
    public Predicate<Company> search(String searchText) {
        return company -> (company.getName().toLowerCase()).contains(searchText.toLowerCase());
    }
}
