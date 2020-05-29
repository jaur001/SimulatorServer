package backend.server.searcher;

public enum SearchBy {
    NIF,
    Name,
    Benefits,
    Treasury,
    Salary,
    Job;

    @Override
    public String toString() {
        return this.name();
    }
}
