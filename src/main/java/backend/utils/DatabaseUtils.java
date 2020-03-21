package backend.utils;

import backend.view.loaders.database.elements.DataType;
import backend.view.loaders.database.elements.Restriction;
import backend.view.loaders.database.elements.Field;
import backend.view.loaders.database.elements.Header;

import java.util.*;

public class DatabaseUtils {

    private static List<Header> headers = new LinkedList<>();

    static {
        createRestaurantTable();
        createProviderTable();
        createClientTable();
    }

    private static void createRestaurantTable() {
        Map<String, Field> parameters = new LinkedHashMap<>();
        parameters.put("NIF",new Field(Restriction.PRIMARY_KEY, DataType.integer));
        parameters.put("name",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("telephoneNumber",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("street",new Field(Restriction.NOT_NULL, DataType.text));
        parameters.put("minPrice",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("maxPrice",new Field(Restriction.NOT_NULL, DataType.integer));
        parameters.put("numberTables",new Field(Restriction.NOT_NULL, DataType.integer));
        headers.add(new Header("Restaurant",parameters));
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
    }

    private static void createClientTable() {
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
        headers.add(new Header("Client",parameters));
    }

    public static List<Header> getHeaders(){
        return headers;
    }
}
