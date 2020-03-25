package backend.model.simulables.restaurant;

public class EatingBill {
    private double finalPrice = 0;

    public EatingBill() {
    }

    public EatingBill(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
