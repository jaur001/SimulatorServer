package backend.server.servlets;

import backend.model.simulation.simulator.Simulator;
import backend.utils.DatabaseUtils;

import java.util.List;

public abstract class PageableFrontCommand<T> extends FrontCommand{

    protected void checkPagination(){
        setPage();
        Simulator.stopSimulation();
        setMaxPage();
    }

    protected void setPage() {
        String attribute = request.getParameter("page");
        if(attribute == null) getPage(1);
        else getPage(Integer.parseInt(attribute));
    }

    protected void getPage(int page) {
        setToRequest("page", page);
        List<T> list = getList(page);
        if(list.size() == 0) forward("/wait.jsp");
        setToRequest("list",list);
    }

    protected abstract List<T> getList(int page);


    protected void setMaxPage() {
        int count = getLimit();
        setToRequest("length", getMaxPage(count));
    }

    protected abstract int getLimit();


    protected int getMaxPage(int count) {
        int maxPage = count / DatabaseUtils.getPageLength();
        return isMultiple(count) ?maxPage:maxPage+1;
    }

    private boolean isMultiple(int count) {
        return count% DatabaseUtils.getPageLength()==0;
    }
}
