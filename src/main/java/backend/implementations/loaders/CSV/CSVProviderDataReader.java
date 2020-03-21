package backend.implementations.loaders.CSV;

import backend.model.simulables.provider.Provider;
import backend.view.loaders.reader.GenericDataReader;

import java.util.Arrays;

public class CSVProviderDataReader implements GenericDataReader<Provider> {
    @Override
    public Provider readData(Object document) {
        String[] data = (String[]) document;
        return new Provider(data[0], data[1],data[2],data[3],data[4]);
    }
}
