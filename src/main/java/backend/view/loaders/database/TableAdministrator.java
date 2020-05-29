package backend.view.loaders.database;

import backend.model.bill.generator.XMLBill;
import backend.view.loaders.database.elements.Selector;
import backend.view.loaders.database.elements.selectors.EqualSelector;

import java.sql.SQLException;
import java.util.List;

public interface TableAdministrator {

    List readPage(int page,Class simulableClass) throws SQLException, ClassNotFoundException;

    List read(int initialValue ,int count,Class simulableClass) throws SQLException, ClassNotFoundException;

    int readCount(Class simulableClass) throws SQLException, ClassNotFoundException;

    void save(Object object) throws SQLException, ClassNotFoundException;

    void deleteAll(Class simulableClass) throws SQLException, ClassNotFoundException;

    void updateRow(Class simulableClass, EqualSelector[] equalSelectors, Selector... selectors) throws SQLException, ClassNotFoundException;
}
