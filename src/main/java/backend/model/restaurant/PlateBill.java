package backend.model.restaurant;

public class PlateBill {
    private double finalPrice = 0;

    public PlateBill() {
    }

    public PlateBill(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
