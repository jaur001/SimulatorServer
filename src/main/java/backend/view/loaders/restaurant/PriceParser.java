package backend.view.loaders.restaurant;

import backend.implementations.loaders.restaurant.tripAvisor.PriceRange;
import org.jsoup.nodes.Document;

public interface PriceParser {
    PriceRange getPrice(Document doc);
}
