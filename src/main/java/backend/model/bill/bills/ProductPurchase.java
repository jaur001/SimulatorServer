package backend.model.bill.bills;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulation.settings.settingsList.BillSettings;

public class ProductPurchase extends CFDIBill{

    private static final Type type = Type.income;
    private static final Use use = Use.G01;
    private Provider provider;
    private ComplexCompany company;


    public ProductPurchase(Provider provider, ComplexCompany company) {
        super(company.getStreet(), type, use, provider.getName()
                ,provider.getNIF(), company.getName(), company.getNIF(), provider.getPrice()
                , BillSettings.getConcept(ProductPurchase.class.getSimpleName()));
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
        return this.getReceiverName() + " has bought "
                + this.getProvider().getProduct() + " to "
                + this.getIssuerName() + ", amount: "
                + this.getTotal();
    }
}
