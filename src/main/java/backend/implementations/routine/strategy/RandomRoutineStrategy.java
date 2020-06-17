package backend.implementations.routine.strategy;

import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.utils.MathUtils;

public class RandomRoutineStrategy implements RoutineStrategy {
    @Override
    public Restaurant getRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(MathUtils.random(0, restaurants.length))];
    }
}
