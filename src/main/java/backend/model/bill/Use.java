package backend.model.bill;

import java.util.Arrays;

public enum Use {
    G01,
    G02,
    G03,
    I01,
    I02,
    I03,
    I04,
    I05,
    I06,
    I07,
    I08,
    NO1;

    public static Use createUse(Object parameter) {
        return Arrays.stream(Use.values()).filter(use -> use.toString().equals(parameter)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return this.name();
    }
}
