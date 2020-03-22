package backend.model.NIFCreator;

import java.util.concurrent.atomic.AtomicInteger;

public class BillNIFCreator implements NIFCreator {

    public static final int INITIAL_VALUE = 4000000;
    private static final AtomicInteger count = new AtomicInteger(INITIAL_VALUE);

    @Override
    public int create() {
        return count.getAndIncrement();
    }
}
