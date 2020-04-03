package backend.model.simulables.company.restaurant;

public class PriceRange {
    private int minPrice;
    private int maxPrice;

    public PriceRange(int minPrice,int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public PriceRange() {
        this.minPrice = 0;
        this.maxPrice = 0;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public boolean isAvailable(){
        return minPrice > 0 && maxPrice > minPrice;
    }

    public String toString(){
        return minPrice + " € - " + maxPrice + " €";
    }
}
