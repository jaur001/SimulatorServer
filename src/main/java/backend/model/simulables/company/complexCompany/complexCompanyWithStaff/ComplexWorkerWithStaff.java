package backend.model.simulables.company.complexCompany.complexCompanyWithStaff;

import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.administration.Contract;
import backend.model.simulables.person.worker.Worker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ComplexWorkerWithStaff extends ComplexCompany {

    protected AdministratorWithStaff administratorWithStaff;
    protected Employer employer;
    protected List<Worker> workers;

    public ComplexWorkerWithStaff(int NIF, String companyName, String street, String telephoneNumber) {
        super(NIF, companyName, street, telephoneNumber);
        workers = new CopyOnWriteArrayList<>();
        this.administratorWithStaff = new AdministratorWithStaff(financialData,this);
        employer = initEmployer();
    }

    protected abstract Employer initEmployer();

    public List<Worker> getWorkers() {
        return workers;
    }

    public void addWorker(Worker worker) {
        worker.hire(this,worker.getSalaryDesired());
        workers.add(worker);
        financialData.addDebt(worker,worker.getSalary());
    }

    public void removeWorker(Worker worker) {
        if(workers.contains(worker)){
            worker.fire();
            financialData.removeDebt(worker);
        }
    }

    public void retireWorker(Worker worker) {
        if(workers.contains(worker)){
            worker.retire();
            financialData.removeDebt(worker);
        }
    }

    public Date getContractExpireDate(Worker worker) {
        return administratorWithStaff.getContractList().stream()
                .filter(contract -> contract.getWorker().equals(worker))
                .map(Contract::getExpireDate)
                .findFirst().orElse(null);
    }
}
