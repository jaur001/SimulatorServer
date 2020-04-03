package backend.model.simulables.company.provider;

public enum  Product {
    Vegetable,
    Meat,
    Fish,
    Wheat,
    Egg,
    Legume,
    Fruit;

    @Override
    public String toString() {
        return this.name();
    }
}
