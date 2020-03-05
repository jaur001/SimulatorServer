package backend.implementations.loaders.CSV;

import backend.model.provider.Provider;
import backend.utils.RestaurantUtils;
import backend.view.loaders.provider.ProviderLoader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProviderLoaderCSV implements ProviderLoader {

    public static final String COMMA = ",";

    @Override
    public List<Provider> load(String url,int rowLength) {
        BufferedReader br = FileLoader.loadFile(url);
        return br != null ? br.lines()
                .map(this::readProvider)
                .limit(rowLength)
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    private Provider readProvider(String line) {
        String[] data = line.split(COMMA);
        return new Provider(data, RestaurantUtils.intialSocialCapital);
    }
}
