package backend.implementations.routine;

import backend.model.simulables.client.Client;
import backend.model.simulables.restaurant.Restaurant;
import backend.utils.BillsUtils;
import backend.model.simulables.client.routineList.routineListController.AmountGenerator;

public class DistributionAmountGenerator implements AmountGenerator {
    @Override
    public double generate(Restaurant restaurant, Client client) {
        return BillsUtils.getPriceApproximation(restaurant,BillsUtils.getPlateNumberSample(), client.getPeopleInvited());
    }
}
