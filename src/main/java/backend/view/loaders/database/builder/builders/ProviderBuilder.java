package backend.view.loaders.database.builder.builders;

import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class ProviderBuilder extends Builder<Provider> {

    @Override
    public String getHeader() {
        return "Provider";
    }

    @Override
    protected List<Object> getRow(Provider provider) {
        return Arrays.asList(new Object[]{provider.getNIF(),provider.getName()
                ,provider.getCreationDate(),provider.getOwnerName()
                ,provider.getStreet(),provider.getTelephoneNumber()});
    }

    @Override
    protected Provider getItem(Object[] parameters) {
        return new Provider((int)parameters[0],(String) parameters[1]
                ,(String)parameters[2],(String)parameters[3]
                ,(String)parameters[4],(String)parameters[5]);
    }
}
