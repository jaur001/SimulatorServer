package backend.server.searcher.comparators;

public interface SearchComparator<DataType>{

    boolean contains(DataType object1, DataType object2);
}
