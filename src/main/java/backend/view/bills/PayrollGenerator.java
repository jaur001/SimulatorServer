package backend.view.bills;

import backend.model.restaurant.worker.Payroll;

public interface PayrollGenerator {
    void generatePayroll(Payroll payroll);
}
