package backend.view.loaders.database.elements;

public enum Restriction {
    PRIMARY_KEY,
    NOT_NULL,
    NOT_NULL_UNIQUE;

    @Override
    public String toString() {
        return this.name().replace("_"," ");
    }

    public boolean equals(Restriction restriction){
        return this.name().equals(restriction.name());
    }
}
