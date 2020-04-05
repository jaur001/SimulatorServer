package backend.implementations.routine.creator;

import backend.model.simulables.company.restaurant.Restaurant;

import java.util.Arrays;

public class BestRoutineCreator extends RandomRoutineCreator{

    @Override
    protected Restaurant getRestaurant(Restaurant[] restaurants) {
        if(restaurants.length==1)return restaurants[0];
        return Arrays.stream(restaurants)
                .reduce(null, this::getBetterScore);
    }

    private Restaurant getBetterScore(Restaurant restaurant1, Restaurant restaurant2) {
        return restaurant1.getScore()>restaurant2.getScore()?restaurant1:restaurant2;
    }
}
