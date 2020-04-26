package backend.utils;

import backend.model.NIFCreator.BillNIFCreator;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.NIFCreator.SecondaryNIFCreator;
import backend.model.NIFCreator.RestaurantNIFCreator;
import backend.view.loaders.database.elements.DataType;
import backend.view.loaders.database.elements.Restriction;
import backend.view.loaders.database.elements.Field;
import backend.view.loaders.database.elements.Header;

import java.util.*;

public class DatabaseUtils {

    public static final int LIST_LIMIT = 1000;
    private static List<Header> headers = new LinkedList<>();
    private static int pageLength = 30;

    static {
        createRestaurantTable();
        createProviderTable();
        createPersonTable();
        createBillTable();
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
        headers.get(3).setInitialPrimaryKeyValue(BillNIFCreator.getInitialValue());
    }

    public static int getPageLength() {
        return pageLength;
    }

    public static List<Header> getHeaders(){
        return headers;
    }

    public static int getListLimit() {
        return LIST_LIMIT;
    }
}
