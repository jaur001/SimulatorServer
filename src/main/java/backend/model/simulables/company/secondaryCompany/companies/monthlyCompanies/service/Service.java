package backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service;

public enum Service {
    Cleaning,
    Transport;

    @Override
    public String toString() {
        return this.name();
    }
}