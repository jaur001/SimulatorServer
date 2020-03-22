package backend.model.bill.generator;

import backend.implementations.database.SQLite.controllers.SQLiteTableInsert;
import backend.model.bill.CFDIBill;
import backend.model.bill.Type;
import backend.model.simulation.Simulation;
import backend.view.loaders.database.builder.builders.BillBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CFDIBillGenerator implements BillGenerator {
    private static String urlSales = "./xmlFiles/EatingBills/";
    private static String urlPayrolls = "./xmlFiles/Payrolls/";
    private Document document;
    private CFDIBill bill;


    public static String getUrlSales() {
        return urlSales;
    }

    public static void setUriSales(String urlSales) {
        CFDIBillGenerator.urlSales = urlSales;
    }

    public static String getUrlPayrolls() {
        return urlPayrolls;
    }

    public static void setUriPayrolls(String urlPayrolls) {
        CFDIBillGenerator.urlPayrolls = urlPayrolls;
    }

    public void generateBill(CFDIBill bill){
        this.bill = bill;
        System.out.println("New Bill -> Issuer : " + bill.getIssuerName() + ", Receiver: " + bill.getReceiverName() + ", amount: " + bill.getSubtotal());
        try {
            createBill();

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private void createBill() throws ParserConfigurationException, TransformerException {
        getXMLDocument();
        appendData();
        saveXMLInFile();
        saveInList();
        saveInDatabase();
    }

    private void getXMLDocument() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    private void appendData() {
        Element billElement = appendVoucher();
        appendIssuerData(billElement);
        appendReceiverData(billElement);
    }

    private Element appendVoucher() {
        Element billElement = document.createElement("cfdi:Voucher");
        setAttributes(billElement);
        document.appendChild(billElement);
        return billElement;
    }

    private void setAttributes(Element billElement) {
        billElement.setAttribute("Date", bill.getDate().toString());
        billElement.setAttribute("Type",bill.getType().toString());
        billElement.setAttribute("Location", bill.getStreet());
        billElement.setAttribute("Currency", bill.getCurrency());
        billElement.setAttribute("SubTotal", bill.getSubtotal()+"");
        billElement.setAttribute("TaxRate", bill.getTaxRate()+"");
        billElement.setAttribute("Total", bill.getTotal()+"");
    }

    private void appendIssuerData(Element billElement) {
        Element clientElement = appendElement("cfdi:Transmitter");
        clientElement.setAttribute("IssuerName", bill.getIssuerName());
        clientElement.setAttribute("IssuerRFC", bill.getIssuerRFC()+"");
        billElement.appendChild(clientElement);
    }

    private void appendReceiverData(Element billElement) {
        Element restaurantElement = appendElement("cfdi:Receiver");
        restaurantElement.setAttribute("ReceiverName", bill.getReceiverName());
        restaurantElement.setAttribute("IssuerRFC", bill.getReceiverRFC()+"");
        billElement.appendChild(restaurantElement);

    }


    private Element appendElement(String name) {
        return document.createElement(name);
    }

    private void saveXMLInFile() throws TransformerException {
        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(getFilePath()+getFileName()));
        transformer.transform(source, result);
    }

    private String getFilePath() {
        return (bill.getType()== Type.income?urlSales:urlPayrolls);
    }

    private String getFileName() {
        return "Bill" + bill.getUUID() + ".xml";
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }

    private void saveInList() {
        Simulation.addBill(new XMLBill(bill, getFilePath(),getFileName()));
    }

    private void saveInDatabase() {
        try {
            new SQLiteTableInsert().insert("Bill", new BillBuilder().buildRow(new XMLBill(bill, getFilePath(),getFileName())));
        } catch (SQLException | ClassNotFoundException e) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                saveInDatabase();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
