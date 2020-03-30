package backend.model.NIFCreator;

import java.util.concurrent.atomic.AtomicInteger;

public class ProviderNIFCreator implements NIFCreator{

    private static final int INITIAL_VALUE = 2000000;
    private static final AtomicInteger count = new AtomicInteger(INITIAL_VALUE);

    public static int getInitialValue(){
        return INITIAL_VALUE;
    }

    @Override
    public int create() {
        return count.getAndIncrement();
    }

}
