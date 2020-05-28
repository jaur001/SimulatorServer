package backend.model.NIFCreator;

import backend.model.simulation.administration.centralControl.SimulationBillAdministrator;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class BillNIFCreator implements NIFCreator {

    private static int initialValue = 0;
    private static final int INITIAL_VALUE = 4000000;
    private static AtomicInteger count;

    public static int getInitialValue() {
        if(initialValue == -1) initInitialValue();
        return initialValue;
    }

    public static void initInitialValue() {
        try {
            initialValue = INITIAL_VALUE + SimulationBillAdministrator.getBillCount();
        } catch (SQLException | ClassNotFoundException e) {
            initialValue = INITIAL_VALUE;
        }
        count = new AtomicInteger(initialValue);
    }

    @Override
    public int create() {
        return count.getAndIncrement();
    }
}
