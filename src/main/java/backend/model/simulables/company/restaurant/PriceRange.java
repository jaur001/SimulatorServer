package backend.model.simulables.company.restaurant;


public class PriceRange {
    public static final int MIN_MIN_PRICE = 3;
    private static final int MAX_MIN_PRICE = 5;
    private double minPrice;
    private double maxPrice;

    public PriceRange(double minPrice,double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public PriceRange() {
        this.minPrice = 0;
        this.maxPrice = 0;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public boolean isAvailable(){
        return minPrice > 0 && maxPrice > minPrice;
    }

    public String toString(){
        return minPrice + " € - " + maxPrice + " €";
    }

    public void increasePrice(double percentage) {
        minPrice *= (1+ percentage);
        maxPrice *= (1+ percentage);
        minPrice = Math.max(minPrice, MIN_MIN_PRICE);
        maxPrice = Math.max(maxPrice,MAX_MIN_PRICE);
    }

    public void decreasePrice(double percentage) {
        minPrice *= (1- percentage);
        maxPrice *= (1- percentage);
    }
}
