package backend.model.bill.generator;

import backend.model.bill.CFDIBill;

public class XMLBill extends CFDIBill {

    private String fileName = "";
    private String filePath = "";

    public XMLBill(CFDIBill bill, String filePath, String fileName) {
        super(bill.getUUID(),bill.getStreet(), bill.getType(), bill.getUse(), bill.getIssuerName(), bill.getIssuerRFC(), bill.getReceiverName(), bill.getReceiverRFC(), bill.getSubtotal(), bill.getConcept());
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public XMLBill() {
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMessage() {
        return "New XML Bill generated.";
    }
}
