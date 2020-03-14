package backend.model.bill.generator;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;

public class XMLBill extends CFDIBill {

    private String filePath;

    public XMLBill(String street, Type type, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double subtotal, String filePath) {
        super(street, type, issuerName, issuerRFC, receiverName, receiverRFC, subtotal);
        this.filePath = filePath;
    }


    public String getFilePath() {
        return filePath;
    }
}
