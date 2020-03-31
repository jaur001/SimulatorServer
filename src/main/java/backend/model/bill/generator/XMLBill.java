package backend.model.bill.generator;

import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulation.TimeLine;

import java.util.Date;

public class XMLBill extends CFDIBill {

    private String fileName = "";
    private String filePath = "";

    public XMLBill(CFDIBill bill, String filePath, String fileName) {
        super(bill.getUUID(),bill.getStreet(), bill.getType(), bill.getIssuerName(), bill.getIssuerRFC(), bill.getReceiverName(), bill.getReceiverRFC(), bill.getTotal(), bill.getConcept());
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
}
