package backend.server.commands.search;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.SearchableFrontCommand;
import backend.utils.SearchUtils;

import java.util.List;

public class SearchProvidersCommand extends SearchableFrontCommand<Provider> {

    @Override
    public List<Provider> search(String text, SearchBy searchBy) {
        return Search.searchProviders(SearchUtils.getCompanyFilter(searchBy).search(text));
    }

    @Override
    public void process() {
        checkPagination();
        forward("/providers.jsp");
    }

    @Override
    protected String getName() {
        return "providerList";
    }
}