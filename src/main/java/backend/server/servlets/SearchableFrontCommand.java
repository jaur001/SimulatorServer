package backend.server.servlets;

import backend.server.searcher.SearchBy;

import java.util.List;

public abstract class SearchableFrontCommand<Simulable> extends PageableFrontCommand<Simulable> {

    protected List<Simulable> search() {
        String text = request.getParameter("searchText");
        SearchBy searchBy = SearchBy.valueOf(request.getParameter("searchBy"));
        return search(text, searchBy);
    }


    protected abstract List<Simulable> search(String text, SearchBy searchBy);

    @Override
    protected List<Simulable> loadList() {
        return search();
    }
}
