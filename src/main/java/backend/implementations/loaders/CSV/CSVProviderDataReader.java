package backend.implementations.loaders.CSV;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.view.loaders.reader.GenericDataReader;

public class CSVProviderDataReader implements GenericDataReader<Provider> {
    @Override
    public Provider readData(Object document) {
        String[] data = (String[]) document;
        return new Provider(data[0], data[1],data[2],data[3],data[4]);
    }
}
