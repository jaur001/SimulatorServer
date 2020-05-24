package backend.model.bill.generator;

import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.model.bill.CFDIBill;
import backend.model.event.EventGenerator;
import backend.model.simulation.administration.centralControl.SimulationBillAdministrator;
import backend.model.simulation.administration.SimulatorThreadPool;
import backend.utils.MathUtils;
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

public class CFDIBillGenerator extends EventGenerator implements BillGenerator {
    private static String uri= "./out/artifacts/RestaurantSimulator_war_exploded/xmlFiles";
    private Document document;
    private CFDIBill bill;


    public static String getUri() {
        return uri;
    }

    public static void setUri(String uri) {
        CFDIBillGenerator.uri = uri;
    }

    public void generateBill(CFDIBill bill){
        this.bill = bill;
        try {
            createBill();
            addEvent(bill);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private void createBill() throws ParserConfigurationException, TransformerException {
        getXMLDocument();
        appendData();
        //saveXMLInFile();
        saveInList();
        //saveInDatabase(bill,getFilePath(),getFileName());
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
        billElement.setAttribute("UUID", bill.getUUID()+"");
        billElement.setAttribute("Date", bill.getDate());
        billElement.setAttribute("Type",bill.getType().toString());
        billElement.setAttribute("Use",bill.getUse().toString());
        billElement.setAttribute("Location", bill.getStreet());
        billElement.setAttribute("Currency", bill.getCurrency());
        billElement.setAttribute("SubTotal", bill.getSubtotal()+"");
        billElement.setAttribute("TaxRate", bill.getTaxRate()+"");
        billElement.setAttribute("Total", bill.getTotal()+"");
        billElement.setAttribute("Concept", bill.getConcept());
    }

    private void appendIssuerData(Element billElement) {
        Element clientElement = appendElement("cfdi:Issuer");
        clientElement.setAttribute("IssuerName", bill.getIssuerName());
        clientElement.setAttribute("IssuerRFC", bill.getIssuerRFC()+"");
        billElement.appendChild(clientElement);
    }

    private void appendReceiverData(Element billElement) {
        Element restaurantElement = appendElement("cfdi:Receiver");
        restaurantElement.setAttribute("ReceiverName", bill.getReceiverName());
        restaurantElement.setAttribute("ReceiverRFC", bill.getReceiverRFC()+"");
        billElement.appendChild(restaurantElement);

    }


    private Element appendElement(String name) {
        return document.createElement(name);
    }

    private void saveXMLInFile() throws TransformerException {
        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(document);
        File file = new File(getFilePath() + "/" + getFileName());
        saveInDirectory(transformer, source, file);
    }

    private void saveInDirectory(Transformer transformer, DOMSource source, File file) throws TransformerException {
        File filePath = new File(getFilePath());
        if(filePath.isDirectory()) transformXML(transformer, source, file);
        else if(filePath.mkdirs()) transformXML(transformer, source, file);
    }

    private void transformXML(Transformer transformer, DOMSource source, File file) throws TransformerException {
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

    private String getFilePath() {
        return uri + bill.getClass().getSimpleName();
    }

    private String getFileName() {
        return "Bill" + bill.getUUID() + ".xml";
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }

    private void saveInList() {
        SimulationBillAdministrator.addBill(new XMLBill(bill, getFilePath(),getFileName()));
    }

    public static void saveInDatabase(CFDIBill bill, String filePath, String fileName) {
        SimulatorThreadPool.getExecutor().submit(() -> {
            try {
                new SQLiteTableInsert().insert("Bill", new BillBuilder().buildRow(new XMLBill(bill, filePath,fileName)));
            } catch (SQLException | ClassNotFoundException e) {
                try {
                    TimeUnit.MILLISECONDS.sleep(MathUtils.random(100,500));
                    saveInDatabase(bill, filePath, fileName);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
