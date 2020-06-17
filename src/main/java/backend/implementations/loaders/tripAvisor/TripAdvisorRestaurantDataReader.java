package backend.implementations.loaders.tripAvisor;

import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.PriceRange;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulation.settings.settingsList.RestaurantSettings;
import backend.view.loaders.reader.GenericDataReader;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import backend.utils.MathUtils;

public class TripAdvisorRestaurantDataReader implements GenericDataReader<Restaurant> {

    public static final int MINPRICE_MIN = 5;
    public static final int MINPRICE_MAX = 15;
    public static final int MAXPRICE_MIN = 20;
    public static final int MAXPRICE_MAX = 50;
    public static final String NAME = "ui_header h1";
    public static final String TELEPHONE = "detail  ui_link level_4 is-hidden-mobile";
    public static final String STREET = "street-address";
    private static double intialSocialCapital = 10000;

    @Override
    public Restaurant readData(Object document) {
        Document doc = (Document) document;
        PriceRange prices = new TripAdvisorPriceParser().getPrice(doc).isAvailable()? new TripAdvisorPriceParser().getPrice(doc): addRandomPrice();
        return new Restaurant(getName(doc),getTelephoneNumber(doc),getStreet(doc),prices,
                RestaurantSettings.getNumTablesSample());
    }

    private PriceRange addRandomPrice() {
        return new PriceRange(generatePrice(MINPRICE_MIN, MINPRICE_MAX),generatePrice(MAXPRICE_MIN, MAXPRICE_MAX));
    }

    private int generatePrice(int min,int max){
        return MathUtils.random(min, max);
    }



    private String getName(Document doc){
        Elements nameList = doc.getElementsByClass(NAME);
        return nameList.first().text();
    }

    private String getTelephoneNumber(Document doc){
        Elements numberList = doc.getElementsByClass(TELEPHONE);
        if (numberList.hasText())return numberList.first().text();
        return "";
    }

    private String getStreet(Document doc) {
        Elements streetList = doc.getElementsByClass(STREET);
        if (streetList.hasText()) {
            return streetList.first().text();
        }
        return "";
    }
}
