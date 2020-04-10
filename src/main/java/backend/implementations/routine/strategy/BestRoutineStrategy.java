package backend.implementations.routine.strategy;

import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulation.Simulation;
import backend.utils.MathUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BestRoutineStrategy implements RoutineStrategy {
    @Override
    public Restaurant getRestaurant(Restaurant[] restaurants) {
        if(restaurants.length==0) return getRandomRestaurant();
        List<Restaurant> halfBest = getHalfBest(restaurants);
        if(halfBest.size()==0) return getRandomRestaurant();
        return halfBest.get(MathUtils.random(0,halfBest.size()));
    }

    public Restaurant getRandomRestaurant() {
        return Simulation.getRestaurantList().get(MathUtils.random(0,Simulation.getRestaurantList().size()));
    }

    private List<Restaurant> getHalfBest(Restaurant[] restaurants) {
        List<Restaurant> halfBest = new LinkedList<>();
        IntStream.range(0,restaurants.length/2).boxed()
                .forEach(integer -> halfBest.add(getBetterRestaurant(restaurants, halfBest)));
        return halfBest;
    }

    private Restaurant getBetterRestaurant(Restaurant[] restaurants, List<Restaurant> halfBest) {
        return Arrays.stream(restaurants)
                .filter(restaurant -> !halfBest.contains(restaurant))
                .reduce(restaurants[0], this::getBetterScore);
    }

    private Restaurant getBetterScore(Restaurant restaurant1, Restaurant restaurant2) {
        return restaurant1.getScore()>restaurant2.getScore()?restaurant1:restaurant2;
    }
}
