package backend.view.loaders;

import backend.model.simulables.person.client.Client;
import java.util.List;

public interface ClientLoader {
    List<Client> load(int rowLength);
}
