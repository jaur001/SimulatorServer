package backend.implementations.xmlBills;

import backend.model.client.Client;
import backend.model.restaurant.Eating;
import backend.model.restaurant.Restaurant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import backend.view.bills.BillGenerator;

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
import java.util.concurrent.atomic.AtomicInteger;

public class CFDIBillGenerator implements BillGenerator {
    private static final AtomicInteger billsCount = new AtomicInteger();
    private static String url = "./xmlFiles/EatingBills/";
    private Document document;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        CFDIBillGenerator.url = url;
    }

    public void generateBill(Eating eating){
        System.out.println("New Bill -> Client : " + eating.getClient().getFirstName() + ", Restaurant: " + eating.getRestaurant().getName() + ", amount: " + eating.getBill().getFinalPrice());
        try {
            createBill(eating);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private void createBill(Eating eating) throws ParserConfigurationException, TransformerException {
        getXMLDocument();
        appendData(eating);
        saveXMLInFile();
    }

    private void getXMLDocument() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    private void appendData(Eating eating) {
        Element bill = appendVoucher(eating);
        appendClientData(eating.getClient(), bill);
        appendRestaurantData(eating.getRestaurant(), bill);
    }

    private Element appendVoucher(Eating eating) {
        Element bill = document.createElement("cfdi:Voucher");
        setAttributes(eating, bill);
        document.appendChild(bill);
        return bill;
    }

    private void setAttributes(Eating eating, Element bill) {
        bill.setAttribute("Date",eating.getDate().toString());
        bill.setAttribute("paymentType","1");
        bill.setAttribute("Location",eating.getRestaurant().getStreet());
        bill.setAttribute("Currency", "Euro");
        bill.setAttribute("Amount", eating.getBill().getFinalPrice()+"");
    }

    private void appendClientData(Client client, Element bill) {
        Element clientElement = appendElement("cfdi:Transmitter");
        clientElement.setAttribute("NIF", client.getNIF()+"");
        clientElement.setAttribute("name", client.getFirstName()+" "+client.getLastName());
        bill.appendChild(clientElement);
    }

    private void appendRestaurantData(Restaurant restaurant, Element bill) {
        Element restaurantElement = appendElement("cfdi:Receiver");
        restaurantElement.setAttribute("NIF", restaurant.getNIF()+"");
        restaurantElement.setAttribute("name", restaurant.getName());
        bill.appendChild(restaurantElement);

    }


    private Element appendElement(String name) {
        return document.createElement(name);
    }

    private void saveXMLInFile() throws TransformerException {
        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(url + "Eating Bill n°" + billsCount.getAndIncrement()));
        transformer.transform(source, result);
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }
}
