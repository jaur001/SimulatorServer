package backend.server.servlets;

import backend.model.simulation.administration.centralControl.SimulatorSwitcher;
import backend.view.loaders.database.DatabaseManager;

import java.util.List;

public abstract class PageableFrontCommand<T> extends FrontCommand{

    protected void checkPagination(){
        SimulatorSwitcher.stopSimulation();
        setPage();
        setMaxPage();
    }

    protected void setPage() {
        getPage(readPage());
    }

    public int readPage(){
        return request.getParameter("page")!=null?getIntParameter("page"):1;
    }

    protected void getPage(int page) {
        setToRequest("page", page);
        List<T> list = getList();
        if(list.size() == 0) forward("/wait.jsp");
        setToRequest("list",getListToShow(page,list));
    }

    private List<T> getListToShow(int page, List<T> list) {
        int from = DatabaseManager.getFrom(page);
        int to = DatabaseManager.getTo(from,list.size());
        return list.subList(from, to);
    }


    private List<T> getList() {
        return loadList();
    }

    protected abstract String getName();

    protected abstract List<T> loadList();


    protected void setMaxPage() {
        int count = getLimit();
        setToRequest("length", getMaxPage(count));
    }

    protected int getLimit(){
        return getList().size();
    }


    protected int getMaxPage(int count) {
        int maxPage = count / DatabaseManager.getPageLength();
        return isMultiple(count) ?maxPage:maxPage+1;
    }

    private boolean isMultiple(int count) {
        return count% DatabaseManager.getPageLength()==0;
    }
}
