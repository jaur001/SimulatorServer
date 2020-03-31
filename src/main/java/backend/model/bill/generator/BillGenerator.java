package backend.model.bill.generator;

import backend.model.bill.CFDIBill;

public interface BillGenerator {

    void generateBill(CFDIBill bill);
}
