package backend.model.bill.generator;

import backend.implementations.loaders.CSV.FileLoader;
import backend.model.bill.CFDIBill;
import backend.model.bill.Publisher;
import backend.model.event.EventGenerator;
import backend.model.simulation.administration.data.SimulationBillAdministrator;
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
import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class CFDIBillGenerator extends EventGenerator implements BillGenerator {
    private static String uri= "";
    private Document document;
    private CFDIBill bill;

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
        //Publisher.publish(getInvoice());
        addBill();

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
        return uri + bill.getClass().getSimpleName()+"/";
    }

    private String getFileName() {
        return "Bill" + bill.getUUID() + ".xml";
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }

    private String getInvoice() {
        BufferedReader br = FileLoader.loadFile(getFilePath() + "/" + getFileName());
        return br != null ? br.lines()
                .reduce("",String::concat) : "";
    }

    private void addBill() {
        SimulationBillAdministrator.addBill(new XMLBill(bill, getFilePath(),getFileName()));
    }

}
