package backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service;

public enum Service {
    Cleaning,
    Transport;

    @Override
    public String toString() {
        return this.name();
    }
}
