package backend.model.NIFCreator;

import backend.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonNIFCreator implements NIFCreator {
    private static final AtomicInteger count = new AtomicInteger(MathUtils.random(10000000,99999999));

    @Override
    public int create() {
        return count.getAndIncrement();
    }
}
