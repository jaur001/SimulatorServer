package backend.view.loaders.database.builder.builders;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class ServiceCompanyBuilder extends Builder<ServiceCompany> {

    @Override
    public String getHeader() {
        return Provider.class.getSimpleName();
    }

    @Override
    protected List<Object> getRow(ServiceCompany company) {
        return Arrays.asList(new Object[]{company.getNIF(),company.getName()
                ,company.getCreationDate(),company.getOwnerName()
                ,company.getStreet(),company.getTelephoneNumber()});
    }

    @Override
    protected ServiceCompany getItem(Object[] parameters) {
        return new ServiceCompany((int)parameters[0],(String) parameters[1]
                ,(String)parameters[2],(String)parameters[3]
                ,(String)parameters[4],(String)parameters[5]);
    }
}
