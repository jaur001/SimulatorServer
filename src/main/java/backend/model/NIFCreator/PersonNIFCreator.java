package backend.model.NIFCreator;

import backend.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonNIFCreator implements NIFCreator {

    private static final int INITIAL_VALUE = 3000000;
    private static final AtomicInteger count = new AtomicInteger(INITIAL_VALUE);

    public static int getInitialValue(){
        return INITIAL_VALUE;
    }

    public static void reset(){
        count.set(INITIAL_VALUE);
    }

    @Override
    public int create() {
        return count.getAndIncrement();
    }
}
