package backend.model.NIFCreator;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class BillNIFCreator implements NIFCreator {

    private static final int INITIAL_VALUE = getInitialValue();

    public static int getInitialValue() {
        int initialValue = 4000000;
        try {
            return initialValue + new SQLiteTableSelector().readCount("Bill");
        } catch (SQLException | ClassNotFoundException e) {
            return initialValue;
        }
    }

    private static final AtomicInteger count = new AtomicInteger(INITIAL_VALUE);

    @Override
    public int create() {
        return count.getAndIncrement();
    }
}
