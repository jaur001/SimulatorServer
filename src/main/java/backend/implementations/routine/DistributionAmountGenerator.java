package backend.implementations.routine;

import backend.model.simulables.person.client.Client;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.BillSettings;
import backend.model.simulables.person.client.routineList.routineFactory.AmountGenerator;

public class DistributionAmountGenerator implements AmountGenerator {
    @Override
    public double generate(Restaurant restaurant, Client client) {
        return BillSettings.calculatePrice(restaurant, client.getPeopleInvited());
    }
}
