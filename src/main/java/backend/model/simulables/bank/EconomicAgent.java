package backend.model.simulables.bank;

public interface EconomicAgent {

    void pay(double amount);
    void collect(double amount);
}
