package backend.utils;

import java.text.NumberFormat;

public class EuroFormatter {

    private static final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

    public static String formatEuro(double number){
        try{
            return defaultFormat.format(number);
        } catch (Exception e){
            String value = String.valueOf(number);
            return value.substring(0,value.indexOf(",")+3) + " €";
        }
    }

    public static String format(double number){
        return formatEuro(number).replaceAll(" €","");
    }
}