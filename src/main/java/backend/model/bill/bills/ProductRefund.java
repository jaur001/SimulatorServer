package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulation.settings.settingsList.BillSettings;

public class ProductRefund extends CFDIBill {

    private static final Type type = Type.income;
    private static final Use use = Use.G02;
    private Provider provider;
    private Company company;


    public ProductRefund(Provider provider, Company company) {
        super(company.getStreet(), type, use, provider.getCompanyName(),provider.getNIF(), company.getCompanyName(), company.getNIF(), provider.getProductPrice()/30, BillSettings.getConcept("ProductRefund"));
        this.company = company;
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public Company getCompany() {
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
