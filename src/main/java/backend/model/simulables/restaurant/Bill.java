package backend.model.simulables.restaurant;

public class Bill {
    private double finalPrice = 0;

    public Bill() {
    }

    public Bill(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
