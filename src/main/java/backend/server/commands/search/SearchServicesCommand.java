package backend.server.commands.search;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.SearchableFrontCommand;
import backend.utils.SearchUtils;

import java.util.List;

public class SearchServicesCommand extends SearchableFrontCommand<ServiceCompany> {

    @Override
    public List<ServiceCompany> search(String text, SearchBy searchBy) {
        return Search.searchServiceCompanies(SearchUtils.getCompanyFilter(searchBy).search(text));
    }

    @Override
    public void process() {
        checkPagination();
        forward("/services.jsp");
    }

    @Override
    protected String getName() {
        return "serviceList";
    }
}