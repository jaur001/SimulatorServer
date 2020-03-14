package backend.model.bill;

public enum Type {
    payroll,
    egress,
    income;

    @Override
    public String toString() {
        return this.name();
    }
}
