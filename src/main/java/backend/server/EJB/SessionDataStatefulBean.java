package backend.server.EJB;

import backend.model.bill.generator.XMLBill;
import backend.model.simulables.Simulable;
import backend.model.simulables.company.Company;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.timeLine.TimeLine;
import backend.server.searcher.SearchBy;
import backend.server.searcher.searchers.company.CompanyNIFSearch;
import backend.server.searcher.searchers.company.CompanyNameSearch;
import backend.server.searcher.searchers.person.JobSearch;
import backend.server.searcher.searchers.person.PersonNIFSearch;
import backend.server.searcher.searchers.person.PersonNameSearch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Stateful(name = "SessionDataStatefulEJB")
public class SessionDataStatefulBean {

    private List<Company> companyList = new CopyOnWriteArrayList<>();
    private List<Client> clientList = new CopyOnWriteArrayList<>();
    private List<Worker> workerList = new CopyOnWriteArrayList<>();
    private List<Simulable> followedSimulables = new LinkedList<>();

    private AtomicBoolean executing = new AtomicBoolean(true);
    private AtomicBoolean restart = new AtomicBoolean(true);
    private TimeLine timeLine;

    @PostConstruct
    public void init() {
        companyList = new CopyOnWriteArrayList<>();
        clientList = new CopyOnWriteArrayList<>();
        workerList = new CopyOnWriteArrayList<>();
        followedSimulables = new LinkedList<>();
        restart = new AtomicBoolean(true);
        executing = new AtomicBoolean(true);
    }

    @PreDestroy
    public void destroy(){
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public List<Simulable> getFollowedSimulables() {
        return followedSimulables;
    }

    public AtomicBoolean getExecuting() {
        return executing;
    }

    public AtomicBoolean getRestart() {
        return restart;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void initTimeLine(List<Simulable> simulableList){
        timeLine = new TimeLine(simulableList);
    }

    public void reset() {
        companyList = new CopyOnWriteArrayList<>();
        clientList = new CopyOnWriteArrayList<>();
        workerList = new CopyOnWriteArrayList<>();
    }
}
