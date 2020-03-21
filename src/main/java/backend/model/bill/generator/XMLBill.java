package backend.model.bill.generator;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;

public class XMLBill extends CFDIBill {

    private String fileName;
    private String filePath;

    public XMLBill(CFDIBill bill, String filePath, String fileName) {
        super(bill.getUUID(),bill.getStreet(), bill.getType(), bill.getIssuerName(), bill.getIssuerRFC(), bill.getReceiverName(), bill.getReceiverRFC(), bill.getSubtotal());
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }
}
