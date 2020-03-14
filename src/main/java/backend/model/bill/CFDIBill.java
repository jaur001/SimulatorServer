package backend.model.bill;

import backend.model.simulation.TimeLine;
import backend.utils.MathUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CFDIBill {
    private static final AtomicInteger count = new AtomicInteger(MathUtils.random(1000000,9999999));
    protected int UUID;
    protected Date date;
    protected String street;
    protected Type type;
    protected String concept;
    protected String issuerName;
    protected int issuerRFC;
    protected String receiverName;
    protected int receiverRFC;
    protected double subtotal;
    protected double taxRate;
    protected double total;
    protected String currency;

    public CFDIBill(String street, Type type, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double total) {
        UUID = count.getAndIncrement();
        this.street = street;
        this.type = type;
        this.issuerName = issuerName;
        this.issuerRFC = issuerRFC;
        this.receiverName = receiverName;
        this.receiverRFC = receiverRFC;
        this.total = total;
        this.taxRate = 1;
        this.subtotal = this.taxRate*total;
        this.currency = "euro";
        this.concept = "";
        this.date = TimeLine.getDate();
    }

    public int getUUID() {
        return UUID;
    }

    public Date getDate() {
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
}
