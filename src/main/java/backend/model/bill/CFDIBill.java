package backend.model.bill;

import backend.model.NIFCreator.BillNIFCreator;
import backend.model.event.Event;
import backend.model.simulables.Simulable;
import backend.model.simulation.timeLine.TimeLine;
import backend.utils.MathUtils;

import java.util.List;

public abstract class CFDIBill implements Event {
    protected int UUID;
    protected String street;
    protected Type type;
    protected Use use;
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

    public CFDIBill(int UUID,String street, Type type, Use use, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double subtotal, String concept) {
        this.UUID = UUID;
        this.street = MathUtils.random(35000,40000)+"";
        this.type = type;
        this.use = use;
        this.issuerName = issuerName;
        this.issuerRFC = issuerRFC;
        this.receiverName = receiverName;
        this.receiverRFC = receiverRFC;
        this.subtotal = subtotal;
        this.taxRate = (double)MathUtils.random(1,25)/100;
        this.total = (1+ this.taxRate)*subtotal;
        this.currency = "euro";
        this.concept = concept;
        this.date = TimeLine.getDate().toString();
    }

    public CFDIBill(String street, Type type, Use use, String issuerName, int issuerRFC, String receiverName, int receiverRFC, double subtotal, String concept) {
        this(new BillNIFCreator().create(),street,type, use,issuerName,issuerRFC,receiverName,receiverRFC,subtotal,concept);
    }


    public CFDIBill() {
    }

    @Override
    public boolean isFollowed(List<Simulable> simulableList){
        return simulableList.stream()
                .anyMatch(this::isInBill);
    }

    public boolean isInBill(Simulable simulable) {
        return simulable.getNIF()==issuerRFC || simulable.getNIF()==receiverRFC;
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

    public Use getUse() {
        return use;
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

    public void setUse(Use use) {
        this.use = use;
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
