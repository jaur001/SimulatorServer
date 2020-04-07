package backend.implementations.routine.strategy;

import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Simulation;
import backend.utils.MathUtils;

import java.util.Arrays;

public class BestRoutineStrategy implements RoutineStrategy {
    @Override
    public Restaurant getRestaurant(Restaurant[] restaurants) {
        if(restaurants.length==0) return getRandomRestaurant();
        if(restaurants.length==1)return restaurants[0];
        return Arrays.stream(restaurants)
                .reduce(restaurants[0], this::getBetterScore);
    }

    public Restaurant getRandomRestaurant() {
        return Simulation.getRestaurantList().get(MathUtils.random(0,Simulation.getRestaurantList().size()));
    }

    private Restaurant getBetterScore(Restaurant restaurant1, Restaurant restaurant2) {
        return restaurant1.getScore()>restaurant2.getScore()?restaurant1:restaurant2;
    }
}
