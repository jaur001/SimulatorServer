package backend.implementations.xmlBills;

import backend.model.restaurant.Restaurant;
import backend.model.restaurant.worker.Payroll;
import backend.model.restaurant.worker.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import backend.model.time.Year;
import backend.view.bills.PayrollGenerator;

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
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CFDIPayrollGenerator implements PayrollGenerator {
    private static final AtomicInteger billsCount = new AtomicInteger();
    private static String url = "./xmlFiles/Payrolls/";
    private Document document;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        CFDIPayrollGenerator.url = url;
    }

    public void generatePayroll(Payroll payroll) {
        System.out.println("New Payroll -> Worker : " + payroll.getWorker().getJob() + ", Restaurant: " + payroll.getRestaurant().getName());
        try {
            createPayroll(payroll);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void createPayroll(Payroll payroll) throws ParserConfigurationException, TransformerException {
        getXMLDocument();
        appendData(payroll);
        saveXMLInFile();
    }

    private void getXMLDocument() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    private void appendData(Payroll payroll) {
        Element bill = appendVoucher(payroll);
        appendRestaurantData(payroll.getRestaurant(), bill);
        appendWorkerData(payroll.getWorker(),bill);
        appendTimeWorked(bill);
    }

    private Element appendVoucher(Payroll payroll) {
        Element bill = document.createElement("cfdi:Voucher");
        setAttributes(payroll, bill);
        document.appendChild(bill);
        return bill;
    }

    private void setAttributes(Payroll payroll, Element bill) {
        bill.setAttribute("Date",new Date().toString());
        bill.setAttribute("PaymentType","1");
        bill.setAttribute("Location",payroll.getRestaurant().getStreet());
        bill.setAttribute("Currency", "Euro");
        bill.setAttribute("Amount", payroll.getWorker().getSalary()+"");
    }

    private void appendRestaurantData(Restaurant restaurant, Element bill) {
        Element restaurantElement = appendElement("cfdi:Transmitter");
        restaurantElement.setAttribute("NIF", restaurant.getNIF()+"");
        restaurantElement.setAttribute("name", restaurant.getName());
        bill.appendChild(restaurantElement);
    }

    private void appendWorkerData(Worker worker, Element bill) {
        Element clientElement = appendElement("cfdi:Transmitter");
        clientElement.setAttribute("Job", worker.getJob()+"");
        bill.appendChild(clientElement);
    }
    private void appendTimeWorked(Element bill) {
        Element timeWorked = appendElement("cfdi:SettlementPeriod");
        timeWorked.setAttribute("Month", Year.getActualMonth()+"");
        bill.appendChild(timeWorked);
    }

    private Element appendElement(String name) {
        return document.createElement(name);
    }

    private void saveXMLInFile() throws TransformerException {
        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(url + "Payroll n°" + billsCount.getAndIncrement()));
        transformer.transform(source, result);
    }

    private Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        return transformerFactory.newTransformer();
    }
}
