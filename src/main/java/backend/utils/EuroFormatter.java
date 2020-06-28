package backend.utils;

import java.text.NumberFormat;

public class EuroFormatter {

    private static final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

    public static String formatEuro(double number){
        return defaultFormat.format(number);
    }

    public static String format(double number){
        return formatEuro(number).replaceAll(" €","");
    }
}
