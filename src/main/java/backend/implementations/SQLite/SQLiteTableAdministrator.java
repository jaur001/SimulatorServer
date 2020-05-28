package backend.implementations.SQLite;

import backend.implementations.SQLite.controllers.SQLiteRowDeleter;
import backend.implementations.SQLite.controllers.SQLiteTableInsert;
import backend.implementations.SQLite.controllers.SQLiteTableSelector;
import backend.view.loaders.database.TableAdministrator;
import backend.view.loaders.database.builder.Builder;
import backend.view.loaders.database.builder.BuilderController;
import backend.view.loaders.database.elements.OrderBy;
import backend.view.loaders.database.elements.Row;
import backend.view.loaders.database.elements.selectors.LikeSelector;
import backend.view.loaders.database.elements.selectors.OrderBySelector;

import java.sql.SQLException;
import java.util.List;

public class SQLiteTableAdministrator extends DatabaseController implements TableAdministrator {
    @Override
    public List read(int initialValue ,int count,Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = BuilderController.getBuilder(simulableClass);
        init(builder.getHeader());
        return builder.buildList(getRows(getModuleOfInitialValue(initialValue), count));
    }

    @Override
    public List readPage(int page, Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = BuilderController.getBuilder(simulableClass);
        init(builder.getHeader());
        return builder.buildList(getRows(page));
    }

    private int getModuleOfInitialValue(int initialValue) throws SQLException, ClassNotFoundException {
        return getActualHeader(actualHeaderName).getInitialPrimaryKeyValue() + (initialValue % readCount());
    }

    private List<Row> getRows(int initialValue, int count) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(actualHeaderName, initialValue, initialValue+count, new OrderBySelector(getActualHeader(actualHeaderName).getPrimaryKeyName(),OrderBy.DESC), new LikeSelector("NIF","1"));
    }

    private List<Row> getRows(int page) throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().read(actualHeaderName, page, new OrderBySelector(getActualHeader(actualHeaderName).getPrimaryKeyName(),OrderBy.DESC));
    }
    @Override
    public int readCount(Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = BuilderController.getBuilder(simulableClass);
        init(builder.getHeader());
        return readCount();
    }

    private int readCount() throws SQLException, ClassNotFoundException {
        return new SQLiteTableSelector().readCount(actualHeaderName);
    }

    @Override
    public void save(Object object) throws SQLException, ClassNotFoundException {
        Builder builder = BuilderController.getBuilder(object.getClass());
        init(builder.getHeader());
        new SQLiteTableInsert().insert(actualHeaderName,builder.buildRow(object));
    }

    @Override
    public void deleteAll(Class simulableClass) throws SQLException, ClassNotFoundException {
        Builder builder = BuilderController.getBuilder(simulableClass);
        init(builder.getHeader());
        new SQLiteRowDeleter().deleteAll(actualHeaderName);
    }
}
