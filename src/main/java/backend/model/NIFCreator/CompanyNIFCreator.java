package backend.model.NIFCreator;

import backend.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class CompanyNIFCreator implements NIFCreator {
    private static final AtomicInteger count = new AtomicInteger(MathUtils.random(1000000,9999999));
    @Override
    public int create() {
        return count.getAndIncrement();
    }
}
