package backend.model.simulables.bank;


import backend.model.event.Event;
import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.bank.transactions.BuildingInversionTransaction;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.ComplexCompany;
import backend.model.simulation.administration.Simulation;
import backend.model.simulation.timeLine.TimeLine;

public class Bank extends EventGenerator implements Simulable, Event {

    public static void makeTransaction(Transaction transaction){
        transaction.makeTransaction();
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        addEvent(this);
        if(TimeLine.isLastDay()){
            Simulation.getClientListCopy().forEach(Collector::collectSalary);
            Simulation.getCompanyListCopy().forEach(this::payMortgage);
        }
    }

    @Override
    public String[] getSimulable() {
        return new String[]{"Bank"};
    }

    public void payMortgage(Payer payer) {
        if(payer instanceof ComplexCompany) {
            ComplexCompany company = (ComplexCompany) payer;
            new BuildingInversionTransaction(this,company, company.getMortgage()).makeTransaction();
        }
    }

    @Override
    public String getMessage() {
        return "The bank has collect taxes from the companies";
    }

    @Override
    public void pay(double amount) {

    }

    @Override
    public void collect(double amount) {

    }
}
