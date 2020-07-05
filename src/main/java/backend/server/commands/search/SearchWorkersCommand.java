package backend.server.commands.search;

import backend.model.simulables.person.worker.Worker;
import backend.server.searcher.Search;
import backend.server.searcher.SearchBy;
import backend.server.servlets.SearchableFrontCommand;
import backend.utils.SearchUtils;

import java.util.List;

public class SearchWorkersCommand extends SearchableFrontCommand<Worker> {

    @Override
    public List<Worker> search(String text, SearchBy searchBy) {
        return Search.searchWorkers(SearchUtils.getPersonFilter(searchBy).search(text));
    }

    @Override
    public void process() {
        checkPagination();
        forward("/workers.jsp");
    }

    @Override
    protected String getName() {
        return "workerList";
    }
}