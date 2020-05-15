package backend.model.simulables.person.worker;

import java.util.stream.IntStream;

public enum Quality {
    VERY_LOW,
    LOW,
    MEDIUM,
    HIGH,
    VERY_HIGH;

    public int getScore(){
        return 1+ IntStream.range(0,values().length).boxed()
                .filter(position -> values()[position].equals(this)).findFirst().orElse(-1);
    }

    @Override
    public String toString() {
        return this.name().replaceAll("_", " ");
    }
}
