package backend.implementations.loaders.CSV;

import backend.model.simulables.person.client.Client;
import backend.utils.SimulationFileReader;
import backend.view.loaders.reader.GenericReader;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class CSVClientReader implements GenericReader<Client> {
    private static final String COMMA = ",";
    private String url;

    public CSVClientReader(String url) {
        this.url = url;
    }

    public List<Client> read(int count) {
        BufferedReader br = SimulationFileReader.read(url);
        return br != null ? br.lines()
                .map(this::readClient)
                .limit(count)
                .collect(Collectors.toList()) : new LinkedList<>();
    }



    private Client readClient(String line) {
        return new CSVClientDataReader().readData(line.split(COMMA));
    }
}
