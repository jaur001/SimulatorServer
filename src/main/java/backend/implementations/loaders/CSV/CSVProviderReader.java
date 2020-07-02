package backend.implementations.loaders.CSV;

import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.utils.SimulationFileReader;
import backend.view.loaders.reader.GenericReader;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVProviderReader implements GenericReader<Provider> {

    public static final String COMMA = ",";
    private String url;

    public CSVProviderReader(String url) {
        this.url = url;
    }

    @Override
    public List<Provider> read(int count) {
        BufferedReader br = SimulationFileReader.read(url);
        return br != null ? br.lines()
                .map(this::readProvider)
                .limit(count)
                .collect(Collectors.toList()) : new LinkedList<>();
    }

    private Provider readProvider(String line) {
        return new CSVProviderDataReader().readData(line.split(COMMA));
    }
}
