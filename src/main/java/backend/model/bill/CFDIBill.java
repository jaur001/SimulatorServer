package backend.model.bill;

import backend.model.NIFCreator.BillNIFCreator;
import backend.model.simulation.TimeLine;
import backend.utils.MathUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CFDIBill {
    protected int UUID;
    protected String street;
    protected Type type;
    protected String issuerName;
    protected int issuerRFC;
    protected String receiverName;
    protected int receiverRFC;
    protected double total;
    protected double taxRate;
    protected double subtotal;
    protected String currency;
    protected String concept;
    protected String date;

    public CFDIBill(String street, Type type, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double total, String concept) {
        UUID = new BillNIFCreator().create();
        init(street,type,issuerName,issuerRFC,receiverName,receiverRFC,total,concept);
    }

    public CFDIBill(int UUID,String street, Type type, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double total, String concept) {
        this.UUID = UUID;
        init(street,type,issuerName,issuerRFC,receiverName,receiverRFC,total,concept);
    }


    public CFDIBill() {
    }


    public void init(String street, Type type, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double total, String concept) {
        this.street = street;
        this.type = type;
        this.issuerName = issuerName;
        this.issuerRFC = issuerRFC;
        this.receiverName = receiverName;
        this.receiverRFC = receiverRFC;
        this.total = total;
        this.taxRate = 1.0;
        this.subtotal = this.taxRate*total;
        this.currency = "euro";
        this.concept = concept;
        this.date = TimeLine.getDate().toString();
    }



    public int getUUID() {
        return UUID;
    }

    public String getDate() {
        return date;
    }

    public String getStreet() {
        return street;
    }

    public Type getType() {
        return type;
    }

    public String getConcept() {
        return concept;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public int getIssuerRFC() {
        return issuerRFC;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public int getReceiverRFC() {
        return receiverRFC;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public void setIssuerRFC(int issuerRFC) {
        this.issuerRFC = issuerRFC;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverRFC(int receiverRFC) {
        this.receiverRFC = receiverRFC;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
