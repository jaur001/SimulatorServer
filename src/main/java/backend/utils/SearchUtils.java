package backend.utils;

import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.searcher.searchers.company.CompanyBenefitsSearch;
import backend.server.searcher.searchers.company.CompanyNIFSearch;
import backend.server.searcher.searchers.company.CompanyNameSearch;
import backend.server.searcher.searchers.company.CompanyTreasurySearch;
import backend.server.searcher.searchers.person.PersonJobSearch;
import backend.server.searcher.searchers.person.PersonNIFSearch;
import backend.server.searcher.searchers.person.PersonNameSearch;
import backend.server.searcher.searchers.person.PersonSalarySearch;

import java.util.LinkedHashMap;
import java.util.Map;
public class SearchUtils {

    private static Map<SearchBy, Search<Client>> personFilterTable = new LinkedHashMap<>();
    private static Map<SearchBy, Search<Company>> companyFilterTable = new LinkedHashMap<>();


    static {
        personFilterTable.put(SearchBy.NIF,new PersonNIFSearch());
        personFilterTable.put(SearchBy.Name,new PersonNameSearch());
        personFilterTable.put(SearchBy.Job,new PersonJobSearch());
        personFilterTable.put(SearchBy.Salary,new PersonSalarySearch());

        companyFilterTable.put(SearchBy.NIF,new CompanyNIFSearch());
        companyFilterTable.put(SearchBy.Name,new CompanyNameSearch());
        companyFilterTable.put(SearchBy.Benefits,new CompanyBenefitsSearch());
        companyFilterTable.put(SearchBy.Treasury,new CompanyTreasurySearch());
    }

    public static Search<Client> getPersonFilter(SearchBy searchBy){
        return personFilterTable.get(searchBy);
    }

    public static Search<Company> getCompanyFilter(SearchBy searchBy){
        return companyFilterTable.get(searchBy);
    }
}
