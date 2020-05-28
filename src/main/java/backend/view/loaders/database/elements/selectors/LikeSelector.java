package backend.view.loaders.database.elements.selectors;

import backend.view.loaders.database.elements.Selector;

public class LikeSelector implements Selector {

    private String fieldName;
    private String likeText;

    public LikeSelector(String fieldName, String likeText) {
        this.fieldName = fieldName;
        this.likeText = likeText;
    }

    @Override
    public String getInstruction() {
        return " " + fieldName + " like '" + likeText + "'";
    }
}
