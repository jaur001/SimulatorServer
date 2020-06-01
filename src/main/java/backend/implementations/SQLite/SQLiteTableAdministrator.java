package backend.implementations.SQLite;

import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteRowUpdater;
import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.view.loaders.database.TableAdministrator;
import backend.view.loaders.database.builder.Builder;
import backend.view.loaders.database.builder.BuilderController;
import backend.view.loaders.database.elements.Row;
import backend.view.loaders.database.elements.Selector;
import backend.view.loaders.database.elements.selectors.EqualSelector;

import java.sql.SQLException;
import java.util.List;

public class SQLiteTableAdministrator extends DatabaseController implements TableAdministrator {
    @Override
    public List read(int initialValue ,int count,Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = initBuilder(simulableClass);
        return builder.buildList(getRows(getModuleOfInitialValue(initialValue), count));
    }

    @Override
    public List readPage(int page, Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = initBuilder(simulableClass);
        return builder.buildList(getRows(page));
    }

    private int getModuleOfInitialValue(int initialValue) throws SQLException, ClassNotFoundException {
        return getActualHeader().getInitialPrimaryKeyValue() + (initialValue % readCount());
    }

    private List<Row> getRows(int initialValue, int count) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(actualHeaderName, initialValue, initialValue+count);
    }

    private List<Row> getRows(int page) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(actualHeaderName, page);

    }

    @Override
    public int readCount(Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = initBuilder(simulableClass);
        return readCount();
    }
    private int readCount() throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().readCount(actualHeaderName);
    }

    @Override
    public void save(Object object) throws SQLException, ClassNotFoundException {
        Builder builder = initBuilder(object.getClass());
        new SQLiteTableInsert().insert(actualHeaderName,builder.buildRow(object));
    }

    @Override
    public void deleteAll(Class simulableClass) throws SQLException, ClassNotFoundException {
        initBuilder(simulableClass);
        new SQLiteRowDeleter().deleteAll(actualHeaderName);
    }

    @Override
    public void updateRow(Class simulableClass, EqualSelector[] equalSelectors, Selector... selectors) throws SQLException, ClassNotFoundException {
        Builder builder = initBuilder(simulableClass);
        new SQLiteRowUpdater().updateRow(builder.getHeader(),equalSelectors,selectors);
    }

    private Builder initBuilder(Class simulableClass) throws ClassNotFoundException, SQLException {
        Builder builder = BuilderController.getBuilder(simulableClass);
        init(builder.getHeader());
        return builder;
    }
}
