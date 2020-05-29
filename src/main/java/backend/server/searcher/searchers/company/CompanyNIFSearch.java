package backend.server.searcher.searchers.company;

import backend.model.simulables.company.Company;
import backend.server.searcher.Search;
import backend.server.searcher.comparators.StringSearchComparator;

import java.util.function.Predicate;

public class CompanyNIFSearch extends StringSearchComparator implements Search<Company> {
    @Override
    public Predicate<Company> search(String searchText) {
        return company -> contains(company.getNIF()+"",searchText);
    }
}
