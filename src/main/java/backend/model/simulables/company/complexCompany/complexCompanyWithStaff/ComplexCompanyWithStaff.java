package backend.model.simulables.company.complexCompany.complexCompanyWithStaff;

import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.transactions.PayrollTransaction;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ComplexCompanyWithStaff extends ComplexCompany {

    protected AdministratorWithStaff administratorWithStaff;
    protected Employer employer;
    protected List<Worker> workers;

    public ComplexCompanyWithStaff(int NIF, String companyName, String street, String telephoneNumber) {
        super(NIF, companyName, street, telephoneNumber);
        workers = new CopyOnWriteArrayList<>();
        this.administratorWithStaff = new AdministratorWithStaff(financialData,this);
        employer = initEmployer();
    }

    protected abstract Employer initEmployer();

    public List<Worker> getWorkers() {
        return workers;
    }

    @Override
    protected void payCompanyDebts() {
        payProvider();
        payServices();
        payWorkers();
    }

    protected void payWorkers() {
        financialData.getPayrolls()
                .forEach((worker, salary) -> Bank.makeTransaction(new PayrollTransaction(this,worker,salary)));
    }

    public boolean addWorker(Worker worker) {
        if(worker.getCompany()==null){
            worker.hire(this,worker.getSalaryDesired());
            workers.add(worker);
            financialData.addDebt(worker);
            return true;
        }
        return false;
    }

    public void removeWorker(Worker worker) {
        if(workers.contains(worker)){
            worker.fire();
            workers.remove(worker);
            financialData.removeDebt(worker);
        }
    }

    public void retireWorker(Worker worker) {
        if(workers.contains(worker)){
            worker.retire();
            workers.remove(worker);
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
