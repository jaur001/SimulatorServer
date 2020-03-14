package backend.implementations.loaders.CSV;

import backend.model.simulables.client.Client;
import backend.view.loaders.ClientLoader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CSVClientLoader implements ClientLoader{
    private static final String COMMA = ",";
    private String url;

    public CSVClientLoader(String url) {
        this.url = url;
    }

    public List<Client> load(int rowLength) {
        BufferedReader br = FileLoader.loadFile(url);
        return br != null ? br.lines()
                .map(this::loadClient)
                .limit(rowLength)
                .collect(Collectors.toList()) : new ArrayList<>();
    }



    private Client loadClient(String line) {
        String[] data = line.split(COMMA);
        return new Client(data);
    }
}
