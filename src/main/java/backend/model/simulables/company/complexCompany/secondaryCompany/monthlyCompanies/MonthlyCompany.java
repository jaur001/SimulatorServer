package backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies;

import backend.model.simulables.company.complexCompany.Administrator;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.SecondaryCompany;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class MonthlyCompany<Product> extends SecondaryCompany {

    protected Administrator administrator;
    protected List<ComplexCompany> companyClientList;
    protected Product product;
    protected double price;

    public MonthlyCompany(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, creationDate, ownerName, street, telephoneNumber);
        companyClientList = new CopyOnWriteArrayList<>();
        administrator = new Administrator(financialData,this);
        this.product = null;
        this.price = 0;
    }

    public void addClient(ComplexCompany company){
        companyClientList.add(company);
        financialData.addIncome(price);
    }

    public void removeClient(ComplexCompany company){
        if(companyClientList.contains(company)){
            companyClientList.remove(company);
            financialData.removeIncome(price);
        }
    }

    public List<ComplexCompany> getCompanyClientList() {
        return companyClientList;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public void setProduct(Product product) {
        this.product = product;
        setInitialPrice();
    }

    public abstract void setInitialPrice();

    public void setPrice(double productPrice) {
        financialData.removeDebt(getMortgage());
        this.price = productPrice;
        financialData.addDebt(getMortgage());
    }

    @Override
    public double getMortgage() {
        return price * 10;
    }
}
