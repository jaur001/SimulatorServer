package backend.view.bills;

import backend.model.restaurant.Eating;

public interface BillGenerator {

    void generateBill(Eating eating);
}
