package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulation.settings.settingsList.BillSettings;
import backend.utils.EuroFormatter;
import backend.utils.MathUtils;

public class BuildingInversion extends CFDIBill {

    private static final Type type = Type.income;

    private ComplexCompany company;

    public BuildingInversion(Bank bank, ComplexCompany company, double amount) {
        super(company.getStreet(),type, getInversion(),company.getName()
                ,company.getNIF(),bank.getName(), bank.getNIF(),
                amount, BillSettings.getConcept(BuildingInversion.class.getSimpleName()));
        this.company = company;
    }

    public static Use getInversion() {
        return Use.values()[MathUtils.random(3,Use.values().length-1)];
    }

    @Override
    public String getMessage() {
        return "The bank has collect the mortgage from " + company.getName()
                + ", amount: " + EuroFormatter.formatEuro(this.getTotal()) + ".";
    }
}
