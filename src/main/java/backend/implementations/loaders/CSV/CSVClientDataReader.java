package backend.implementations.loaders.CSV;

import backend.model.simulables.client.Client;
import backend.model.simulables.client.PersonalData;
import backend.view.loaders.reader.GenericDataReader;

public class CSVClientDataReader implements GenericDataReader<Client> {
    @Override
    public Client readData(Object document) {
        String[] data = (String[]) document;
        return new Client(new PersonalData(Integer.parseInt(data[0]), data[1],data[2],data[3]
                ,data[4],data[5],data[6]
                ,data[7],data[8],data[9]));

    }
}