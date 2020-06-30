package backend.model.NIFCreator;

import backend.model.simulation.administration.data.SimulationBillAdministrator;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class BillNIFCreator implements NIFCreator {

    private static final int INITIAL_VALUE = 4000000;
    private static final AtomicInteger count = new AtomicInteger(INITIAL_VALUE);

    public static int getInitialValue() {
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
