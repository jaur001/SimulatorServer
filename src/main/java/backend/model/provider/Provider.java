package backend.model.provider;

import backend.model.financialData.ProviderFinancialData;

public class Provider {
    private Product product;
    private String companyName;
    private String creationDate;
    private String ownerName;
    private String street;
    private String telephoneNumber;
    private ProviderFinancialData financialData;
    private double productPrice;

    public Provider(String[] data, double socialCapital) {
        this.product = null;
        this.companyName = data[0];
        this.creationDate = data[1];
        this.ownerName = data[2];
        this.street = data[3];
        this.telephoneNumber = data[4];
        this.financialData = new ProviderFinancialData(socialCapital);
        this.productPrice = 0;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStreet() {
        return street;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Product getProduct() {
        return product;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ProviderFinancialData getFinancialData() {
        return financialData;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
