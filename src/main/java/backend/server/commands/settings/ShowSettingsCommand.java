package backend.server.commands.settings;

import backend.implementations.database.SQLite.controllers.SQLiteTableSelector;
import backend.server.servlets.FrontCommand;

import java.sql.SQLException;

public class ShowSettingsCommand extends FrontCommand {
    @Override
    public void process() {
        try {
            setToRequest("clientLength",new SQLiteTableSelector().readCount("Client"));
            setToRequest("restaurantLength",new SQLiteTableSelector().readCount("Restaurant"));
            setToRequest("providerLength",new SQLiteTableSelector().readCount("Provider"));
        } catch (SQLException | ClassNotFoundException e) {
            setToRequest("clientLength",0);
            setToRequest("restaurantLength",0);
            setToRequest("providerLength",0);
        }
        forward("/settings.jsp");
    }
}
