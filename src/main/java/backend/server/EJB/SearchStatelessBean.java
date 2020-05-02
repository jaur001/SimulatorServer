package backend.server.EJB;

import javax.ejb.Local;
import java.util.List;
import java.util.function.Predicate;

@Local
public interface SearchStatelessBean<Filterable> {

    List<Filterable> search(String searchText);

    Predicate<Filterable> filter(String searchText);
}
