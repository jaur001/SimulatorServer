package backend.view.loaders.provider;

import backend.model.provider.Provider;

import java.util.List;

public interface ProviderLoader {
    List<Provider> load(String url, int rowLength);
}
