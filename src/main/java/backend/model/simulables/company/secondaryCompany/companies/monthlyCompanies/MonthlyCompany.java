package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies;

import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.secondaryCompany.SecondaryCompany;

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
        return (price* ComplexCompany.taxes)/100;
    }
}
