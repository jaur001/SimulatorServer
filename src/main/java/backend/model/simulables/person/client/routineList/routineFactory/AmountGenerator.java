package backend.model.simulables.person.client.routineList.routineFactory;

import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;

public interface AmountGenerator {
    double generate(Restaurant restaurant, Client client);
}
