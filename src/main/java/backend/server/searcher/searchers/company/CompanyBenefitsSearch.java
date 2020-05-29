package backend.server.searcher.searchers.company;

import backend.model.simulables.company.Company;
import backend.server.searcher.Search;
import backend.server.searcher.comparators.DoubleSearchComparator;

import java.util.function.Predicate;

public class CompanyBenefitsSearch extends DoubleSearchComparator implements Search<Company> {
    @Override
    public Predicate<Company> search(String searchText) {
        return company -> checkSearch(company.getFinancialData().getLastMonthBenefits(),searchText);
    }


    @Override
    protected Double getInterval() {
        return 500.0;
    }
}
