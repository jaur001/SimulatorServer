package backend.server.commands.search;

import backend.model.simulables.person.client.Client;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.SearchableFrontCommand;
import backend.utils.SearchUtils;

import java.util.List;


public class SearchClientsCommand extends SearchableFrontCommand<Client> {

    @Override
    public List<Client> search(String text, SearchBy searchBy) {
        return Search.searchClients(SearchUtils.getPersonFilter(searchBy).search(text));
    }

    @Override
    public void process() {
        checkPagination();
        forward("/clients.jsp");
    }

    @Override
    protected String getName() {
        return "clientList";
    }
}
