package backend.implementations.routine.strategy;

import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;

public interface RoutineStrategy {
    Restaurant getRestaurant(Restaurant[] restaurants);
}
