package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.event.Event;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulation.settings.settingsList.BillSettings;

public class ProductPurchase extends CFDIBill{

    private static final Type type = Type.income;
    private static final Use use = Use.G01;
    private Provider provider;
    private Company company;


    public ProductPurchase(Provider provider, Company company) {
        super(company.getStreet(), type, use, provider.getCompanyName(),provider.getNIF(), company.getCompanyName(), company.getNIF(), provider.getProductPrice(), BillSettings.getConcept("ProductPurchase"));
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
        return this.getReceiverName() + " has bought "
                + this.getProvider().getProduct() + " to "
                + this.getIssuerName() + ", amount: "
                + this.getTotal();
    }
}
