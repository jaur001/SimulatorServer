package backend.model.simulables.company.provider;

public enum  Product {
    Vegetable,
    Meat,
    Fish,
    Egg,
    Legume,
    Fruit,
    Others;

    @Override
    public String toString() {
        return this.name();
    }
}
