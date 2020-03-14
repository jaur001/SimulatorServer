package backend.view.loaders.provider;

import backend.model.simulables.provider.Provider;

import java.util.List;

public interface ProviderLoader {
    List<Provider> load(int rowLength);
}
