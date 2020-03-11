package backend.model.restaurant;

import backend.model.time.Time;

import java.util.Date;

public class Bill {
    private String UUID;
    private Date date;
    private String street;
    private String type; //enum futuro
    private String concept;
    private String issuerName;
    private String issuerRFC;
    private String receiverName;
    private String receiverRFC;
    private double subtotal;
    private double taxRate;
    private double total;
    private String currency;
    private String filePath;

    public Bill(String street, String type, String issuerName, String issuerRFC, String receiverName, String receiverRFC, double subtotal, String filePath) {
        this.street = street;
        this.type = type;
        this.issuerName = issuerName;
        this.issuerRFC = issuerRFC;
        this.receiverName = receiverName;
        this.receiverRFC = receiverRFC;
        this.subtotal = subtotal;
        this.taxRate = 1;
        this.total = this.taxRate*subtotal;
        this.currency = "euro";
        this.concept = "";
        this.date = Time.getActualDate();
        this.filePath = filePath;
    }

    public String getUUID() {
        return UUID;
    }

    public Date getDate() {
        return date;
    }

    public String getStreet() {
        return street;
    }

    public String getType() {
        return type;
    }

    public String getConcept() {
        return concept;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public String getIssuerRFC() {
        return issuerRFC;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverRFC() {
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

    public String getFilePath() {
        return filePath;
    }
}
