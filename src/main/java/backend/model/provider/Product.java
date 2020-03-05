package backend.model.provider;

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
