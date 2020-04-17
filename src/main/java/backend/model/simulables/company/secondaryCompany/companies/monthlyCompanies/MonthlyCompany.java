package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies;

import backend.model.simulables.company.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.SecondaryCompany;
import backend.model.simulation.settings.settingsList.ProviderSettings;

import java.util.ArrayList;
import java.util.List;

public abstract class MonthlyCompany<Product> extends SecondaryCompany {

    protected List<ComplexCompany> companyList;
    protected Product product;
    protected double price;

    public MonthlyCompany(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street, telephoneNumber);
        companyList = new ArrayList<>();
        this.product = null;
        this.price = 0;
    }

    public void addClient(ComplexCompany company){
        companyList.add(company);
        financialData.addIncome(price);
    }

    public void removeClient(ComplexCompany company){
        if(companyList.contains(company)){
            companyList.remove(company);
            financialData.removeIncome(price);
        }
    }

    public List<ComplexCompany> getCompanyList() {
        return companyList;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public void setProduct(Product product) {
        this.product = product;
        setPrice();
    }

    public abstract void setPrice();

    public void setPrice(double productPrice) {
        financialData.removeDebt(getTaxes());
        this.price = productPrice;
        financialData.addDebt(getTaxes());
    }

    @Override
    protected boolean manageFinances() {
        return financialData.getTreasury() <= -5000;
    }

    @Override
    protected void increasePrice() {
        price*= (1+ ProviderSettings.PRICE_CHANGE);
    }

    @Override
    protected void decreasePrice() {
        price*= (1-ProviderSettings.PRICE_CHANGE);
    }

    @Override
    protected double getTaxes() {
        return (price* ComplexCompany.TAXES)/100;
    }
}
