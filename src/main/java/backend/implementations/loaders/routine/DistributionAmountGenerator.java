package backend.implementations.loaders.routine;

import backend.model.client.Client;
import backend.model.restaurant.Restaurant;
import backend.model.utils.BillsUtils;
import backend.view.loaders.routine.AmountGenerator;

public class DistributionAmountGenerator implements AmountGenerator {
    @Override
    public double generate(Restaurant restaurant, Client client) {
        return BillsUtils.getPriceApproximation(restaurant,(int) BillsUtils.getPlateNumberSample(), client.getPeopleInvited());
    }
}
