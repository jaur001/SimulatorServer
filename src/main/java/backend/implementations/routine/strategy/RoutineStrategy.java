package backend.implementations.routine.strategy;

import backend.model.simulables.company.restaurant.Restaurant;

public interface RoutineStrategy {
    Restaurant getRestaurant(Restaurant[] restaurants);
}
