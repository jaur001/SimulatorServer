package backend.server.searcher.comparators;

public abstract class StringSearchComparator implements SearchComparator<String> {

    @Override
    public boolean contains(String object1, String object2) {
        return object1.toLowerCase().contains(object2.toLowerCase());
    }
}
