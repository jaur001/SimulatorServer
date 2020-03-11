package backend.implementations.loaders.restaurant.tripAvisor;

import backend.model.restaurant.Restaurant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import backend.view.loaders.restaurant.RestaurantReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripAdvisorRestaurantReader implements RestaurantReader {
    private static final String WEBPAGE = "https://www.tripadvisor.es";
    private static final String HREF = "href";
    private static final String RESTAURANT_COLLECTION = "component_2";
    private static final String RESTAURANT_SELECTOR = "_15_ydu6b";
    private static final String RESTAURANT_WEBPAGE_SEARCHER = "https://www.tripadvisor.es/RestaurantSearch-g187471-oa";
    private static final String HTML_FILE = "-a_date.2019__2D__11__2D__20-a_people.2-a_time.20%3A00%3A00-a_zur.2019__5F__11__5F__20-Gran_Ca.html#EATERY_LIST_CONTENTS";
    private List<Restaurant> restaurantList = new ArrayList<>();

    public List<Restaurant> read(int pages) throws IOException {

        Document doc = connect(pages);
        Elements topRestaurantList = getRestaurantList(doc);
        for (Element i : topRestaurantList){
            restaurantList.add(readRestaurant(i));
            System.out.println(pages + ") " + restaurantList.get(restaurantList.size()-1).getName());
        }

        return restaurantList;
    }

    private Document connect(int j) throws IOException {
        return Jsoup.connect(RESTAURANT_WEBPAGE_SEARCHER + j*30 + HTML_FILE).get();
    }

    private Elements getRestaurantList(Document doc) {
        Element topRestaurant = doc.getElementById(RESTAURANT_COLLECTION);
        return topRestaurant.getElementsByClass(RESTAURANT_SELECTOR);
    }

    private Restaurant readRestaurant(Element i) throws IOException {
        Document doc = connectToRestaurantPage(i);
        return new TripAdvisorRestaurantDataReader().readData(doc);
    }

    private Document connectToRestaurantPage(Element i) throws IOException {
        Document doc;
        doc = Jsoup.connect(WEBPAGE +i.attr(HREF)).get();
        return doc;
    }

}
