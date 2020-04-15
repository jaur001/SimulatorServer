package backend.model.simulables.company.secondaryCompany;

import backend.model.simulables.company.Company;
import backend.model.simulables.company.FinancialData;
import backend.model.simulation.settings.settingsList.ProviderSettings;

public abstract class SecondaryCompany extends Company {

    protected String creationDate;
    protected String ownerName;

    public SecondaryCompany(int NIF, String companyName, String creationDate, String ownerName, String street, String telephoneNumber) {
        super(NIF, companyName, street, telephoneNumber, new FinancialData(ProviderSettings.getInitialSocialCapital()));
        this.creationDate = creationDate;
        this.ownerName = ownerName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    protected void checkFinances() {
        financialData.reset();
        changePrice();
        analyzeFinances();
    }
}
