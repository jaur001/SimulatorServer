package backend.server.EJB;

import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.searcher.searchers.company.CompanyNIFSearch;
import backend.server.searcher.searchers.company.CompanyNameSearch;
import backend.server.searcher.searchers.person.JobSearch;
import backend.server.searcher.searchers.person.PersonNIFSearch;
import backend.server.searcher.searchers.person.PersonNameSearch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import java.util.LinkedHashMap;
import java.util.Map;

@Stateless(name = "SearchControllerStatelessEJB")
public class SearchControllerStatelessBean {

    private Map<SearchBy, Search<Client>> personFilterTable = new LinkedHashMap<>();
    private Map<SearchBy, Search<Company>> companyFilterTable = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        personFilterTable.put(SearchBy.values()[0],new PersonNIFSearch());
        personFilterTable.put(SearchBy.values()[1],new PersonNameSearch());
        personFilterTable.put(SearchBy.values()[2],new JobSearch());

        companyFilterTable.put(SearchBy.values()[0],new CompanyNIFSearch());
        companyFilterTable.put(SearchBy.values()[1],new CompanyNameSearch());
    }

    @PreDestroy
    public void destroy(){
        personFilterTable = null;
        companyFilterTable = null;
    }

    public Search<Client> getPersonFilter(SearchBy searchBy){
        System.out.println(this.getClass().getSimpleName()+ "::getPersonFilter(" + searchBy.name() + ")");
        return personFilterTable.get(searchBy);
    }

    public Search<Company> getCompanyFilter(SearchBy searchBy){
        return companyFilterTable.get(searchBy);
    }
}
