package backend.view.loaders.database;

import backend.model.NIFCreator.BillNIFCreator;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.NIFCreator.SecondaryNIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.view.loaders.database.elements.DataType;
import backend.view.loaders.database.elements.Restriction;
import backend.view.loaders.database.elements.Field;
import backend.view.loaders.database.elements.Header;

import java.util.*;

public class DatabaseManager {

    public static final int LIST_LIMIT = 1000;
    private static List<Header> headers = new LinkedList<>();
    private static final int PAGE_LENGTH = 30;

    static {
        createRestaurantTable();
        createProviderTable();
        createPersonTable();
        createBillTable();
        createGeneralDataTable();
        createClientDataTable();
        createRestaurantDataTable();
        createProviderDataTable();
        createServiceDataTable();
        createWorkerDataTable();
    }

    private static void createRestaurantTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("NIF",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("name",new Field(Restriction.NOT_NULL_UNIQUE, DataType.text));
        parameters.put("telephoneNumber",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("street",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("minPrice",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("maxPrice",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("numberTables",new Field(Restriction.NOT_NULL, DataType.integer));
        headers.add(new Header("Restaurant",parameters));
        headers.get(0).setInitialPrimaryKeyValue(RestaurantNIFCreator.getInitialValue());
    }

    private static void createProviderTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("NIF",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("companyName",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("creationDate",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("ownerName",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("street",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("telephoneNumber",new Field(Restriction.NOT_NULL, DataType.text));
        headers.add(new Header("Provider",parameters));
        headers.get(1).setInitialPrimaryKeyValue(SecondaryNIFCreator.getInitialValue());
    }

    private static void createPersonTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("NIF",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("firstName",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("lastName",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("birthDate",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("gender",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("job",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("country",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("telephoneNumber",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("email",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("cardNumber",new Field(Restriction.NOT_NULL, DataType.text));
        headers.add(new Header("Person",parameters));
        headers.get(2).setInitialPrimaryKeyValue(PersonNIFCreator.getInitialValue());
    }

    private static void createBillTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("UUID",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("street",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("type",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("use",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("issuerName",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("issuerRFC",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("receiverName",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("receiverRFC",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("total",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("taxRate",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("subtotal",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("currency",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("concept",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("date",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("filePath",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("fileName",new Field(Restriction.NOT_NULL_UNIQUE, DataType.text));
        headers.add(new Header("Bill",parameters));
        setBillInitialPrimaryKeyValue();
    }

    private static void createGeneralDataTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("ID",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("clientCount",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("restaurantCount",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("providerCount",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("serviceCount",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("workerCount",new Field(Restriction.NOT_NULL, DataType.integer));
        headers.add(new Header("GeneralData",parameters));
        headers.get(4).setInitialPrimaryKeyValue(0);
    }

    private static void createClientDataTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("ID",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("salaryMean",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("salarySD",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("minSalary",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("invitedPeopleMin",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("invitedPeopleMax",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("numOfRestaurantMin",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("numOfRestaurantMax",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("plateNumberMean",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("plateNumberSD",new Field(Restriction.NOT_NULL, DataType.real));
        headers.add(new Header("ClientData",parameters));
        headers.get(5).setInitialPrimaryKeyValue(0);
    }

    private static void createRestaurantDataTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("ID",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("initialSocialCapital",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("lengthContractMin",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("lengthContractMax",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("priceChange",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("capacity",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("closeLimit",new Field(Restriction.NOT_NULL, DataType.real));
        headers.add(new Header("RestaurantData",parameters));
        headers.get(6).setInitialPrimaryKeyValue(0);
    }

    private static void createProviderDataTable() {
        Map<String, Field> parameters = createMonthlyCompanyTable();
        headers.add(new Header("ProviderData",parameters));
        headers.get(7).setInitialPrimaryKeyValue(0);
    }

    private static void createServiceDataTable() {
        Map<String, Field> parameters = createMonthlyCompanyTable();
        headers.add(new Header("ServiceData",parameters));
        headers.get(8).setInitialPrimaryKeyValue(0);
    }

    private static Map<String, Field> createMonthlyCompanyTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("ID",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("initialSocialCapital",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("priceChange",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("closeLimit",new Field(Restriction.NOT_NULL, DataType.real));
        return parameters;
    }

    private static void createWorkerDataTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("ID",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("minSalary",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("salaryChange",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("salaryDesiredChange",new Field(Restriction.NOT_NULL, DataType.real));
        parameters.put("retireAge",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("percentageRetirement",new Field(Restriction.NOT_NULL, DataType.real));
        headers.add(new Header("WorkerData",parameters));
        headers.get(9).setInitialPrimaryKeyValue(0);
    }

    public static void setBillInitialPrimaryKeyValue(){
        headers.get(3).setInitialPrimaryKeyValue(BillNIFCreator.getInitialValue());
    }

    public static int getPageLength() {
        return PAGE_LENGTH;
    }

    public static List<Header> getHeaders(){
        return headers;
    }

    public static int getListLimit() {
        return LIST_LIMIT;
    }

    public static int getFrom(int page) {
        return PAGE_LENGTH*(page-1);
    }

    public static int getTo(int from, int size) {
        return Math.min(from + PAGE_LENGTH, size);
    }
}
