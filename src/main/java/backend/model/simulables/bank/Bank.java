package backend.model.simulables.bank;


import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.model.event.Event;
import backend.model.event.EventGenerator;
import backend.model.event.events.company.TaxesPayedCompanyEvent;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.transactions.BuildingInversionTransaction;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.model.simulation.timeLine.TimeLine;

import java.util.List;

public class Bank extends EventGenerator implements Simulable, Event {

    public static void makeTransaction(Transaction transaction){
        transaction.makeTransaction();
    }

    @Override
    public void simulate() {
        if(TimeLine.isLastDay()){
            Simulation.getClientListCopy().forEach(Collector::collectSalary);
            Simulation.getCompanyListCopy().forEach(this::payMortgage);
            Simulation.getCompanyListCopy().forEach(this::payTaxes);
            addEvent(this);
        }
    }

    private void payTaxes(Company company) {
        company.pay(Company.getTaxes()*company.getFinancialData().getBenefits());
        addEvent(new TaxesPayedCompanyEvent(company));
    }


    @Override
    public int getNIF() {
        return RestaurantNIFCreator.getInitialValue()-1;
    }

    @Override
    public String getName() {
        return "Bank";
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
    public boolean isFollowed(List<Simulable> simulableList) {
        return true;
    }

    @Override
    public void pay(double amount) {

    }

    @Override
    public void collect(double amount) {

    }
}
