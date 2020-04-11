package backend.implementations.loaders.tripAvisor;

import backend.model.simulables.company.restaurant.Restaurant;
import backend.view.loaders.reader.GenericReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TripAdvisorRestaurantReader implements GenericReader<Restaurant> {
    private static final String WEBPAGE = "https://www.tripadvisor.es";
    private static final String HREF = "href";
    private static final String RESTAURANT_COLLECTION = "component_2";
    private static final String RESTAURANT_SELECTOR = "_15_ydu6b";
    private static final String RESTAURANT_WEBPAGE_SEARCHER = "https://www.tripadvisor.es/RestaurantSearch-g187471-oa";
    private static final String HTML_FILE = "-a_date.2019__2D__11__2D__20-a_people.2-a_time.20%3A00%3A00-a_zur.2019__5F__11__5F__20-Gran_Ca.html#EATERY_LIST_CONTENTS";
    private List<Restaurant> restaurantList = new LinkedList<>();

    public List<Restaurant> read(int page) throws IOException {
        Elements topRestaurantList = getRestaurantList(connect(page));
        for (Element i : topRestaurantList){
            Restaurant restaurant = readRestaurant(i);
            restaurantList.add(restaurant);
            System.out.println(page + ") " + restaurant.getCompanyName());
        }

        return restaurantList;
    }

    private Document connect(int page) throws IOException {
        return Jsoup.connect(RESTAURANT_WEBPAGE_SEARCHER + page*30 + HTML_FILE).get();
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
