package backend.view.loaders.database.elements.selectors;

import backend.view.loaders.database.elements.OrderBy;
import backend.view.loaders.database.elements.Selector;

public class OrderBySelector implements Selector {

    private String fieldName;
    private OrderBy orderBy;

    public OrderBySelector(String fieldName, OrderBy orderBy) {
        this.fieldName = fieldName;
        this.orderBy = orderBy;
    }

    @Override
    public String getInstruction() {
        return " order by " + fieldName + " " + orderBy.toString();
    }
}
