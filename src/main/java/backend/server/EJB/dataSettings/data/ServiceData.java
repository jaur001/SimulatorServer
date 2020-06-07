package backend.server.EJB.dataSettings.data;

import backend.model.simulables.company.secondaryCompany.monthlyCompanies.service.Service;

import java.util.Map;

public class ServiceData {

    private double initialSocialCapital;
    private Map<Service, Double> servicePriceTable;
    private double priceChange;
    private double closeLimit;

    public ServiceData(double initialSocialCapital, Map<Service, Double> servicePriceTable, double priceChange, double closeLimit) {
        this.initialSocialCapital = initialSocialCapital;
        this.servicePriceTable = servicePriceTable;
        this.priceChange = priceChange;
        this.closeLimit = closeLimit;
    }

    public ServiceData(double initialSocialCapital, double priceChange, double closeLimit) {
        this.initialSocialCapital = initialSocialCapital;
        this.priceChange = priceChange;
        this.closeLimit = closeLimit;
    }

    public double getInitialSocialCapital() {
        return initialSocialCapital;
    }

    public Map<Service, Double> getServicePriceTable() {
        return servicePriceTable;
    }

    public void setServicePriceTable(Map<Service, Double> servicePriceTable) {
        this.servicePriceTable = servicePriceTable;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public double getCloseLimit() {
        return closeLimit;
    }
}
