package backend.server.searcher;

public enum SearchBy {
    NIF,
    Name,
    Job;

    @Override
    public String toString() {
        return this.name();
    }
}
