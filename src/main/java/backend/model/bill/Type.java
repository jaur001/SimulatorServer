package backend.model.bill;

import java.util.Arrays;

public enum Type {
    payroll,
    egress,
    income;

    public static Type createType(Object parameter) {
        return Arrays.stream(Type.values()).filter(type -> type.toString().equals(parameter)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return this.name();
    }
}
