package backend.utils;

import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.searcher.searchers.company.CompanyNIFSearch;
import backend.server.searcher.searchers.company.CompanyNameSearch;
import backend.server.searcher.searchers.person.JobSearch;
import backend.server.searcher.searchers.person.PersonNIFSearch;
import backend.server.searcher.searchers.person.PersonNameSearch;

import java.util.LinkedHashMap;
import java.util.Map;

public class SearcherUtils {

    private static Map<SearchBy, Search<Client>> personFilterTable = new LinkedHashMap<>();
    private static Map<SearchBy, Search<Company>> companyFilterTable = new LinkedHashMap<>();


    static {
        personFilterTable.put(SearchBy.values()[0],new PersonNIFSearch());
        personFilterTable.put(SearchBy.values()[1],new PersonNameSearch());
        personFilterTable.put(SearchBy.values()[2],new JobSearch());

        companyFilterTable.put(SearchBy.values()[0],new CompanyNIFSearch());
        companyFilterTable.put(SearchBy.values()[1],new CompanyNameSearch());
    }

    public static Search<Client> getPersonFilter(SearchBy searchBy){
        return personFilterTable.get(searchBy);
    }

    public static Search<Company> getCompanyFilter(SearchBy searchBy){
        return companyFilterTable.get(searchBy);
    }
}
