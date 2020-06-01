package backend.main;

import backend.implementations.SQLite.SQLiteTableAdministrator;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulation.administration.data.SimulationDataController;
import backend.view.loaders.database.TableAdministrator;
import backend.view.loaders.database.elements.selectors.EqualSelector;

import java.sql.SQLException;

public class Lab {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SimulationDataController.initSessionData();
        TableAdministrator administrator = new SQLiteTableAdministrator();
        administrator.updateRow(Provider.class,new EqualSelector[]{new EqualSelector("Street","Hello")},new EqualSelector("NIF","2000000"));
    }

}
