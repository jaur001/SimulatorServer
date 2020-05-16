package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulation.settings.settingsList.BillSettings;

public class ProductRefund extends CFDIBill {

    private static final Type type = Type.egress;
    private static final Use use = Use.G02;
    private Provider provider;
    private ComplexCompany company;


    public ProductRefund(Provider provider, ComplexCompany company) {
        super(company.getStreet(), type, use, provider.getName(),provider.getNIF(),
                company.getName(), company.getNIF(), provider.getPrice()/30,
                BillSettings.getConcept(ProductRefund.class.getSimpleName()));
        this.company = company;
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public ComplexCompany getCompany() {
        return company;
    }

    @Override
    public String getMessage() {
        return this.getReceiverName() + " has refund "
                + this.getProvider().getProduct() + " from "
                + this.getIssuerName() + ", amount: "
                + this.getTotal();
    }
}
