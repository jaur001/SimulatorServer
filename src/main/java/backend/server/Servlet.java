package backend.server;

import backend.implementations.loaders.CSV.ClientLoaderCSV;
import backend.implementations.loaders.CSV.ProviderLoaderCSV;
import backend.implementations.loaders.restaurant.SQLite.SQLRestaurantWriter;
import backend.implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import backend.implementations.xmlBills.CFDIBillGenerator;
import backend.implementations.xmlBills.CFDIPayrollGenerator;
import backend.model.client.Client;
import backend.model.provider.Provider;
import backend.model.restaurant.Restaurant;
import backend.threads.initializers.RestaurantThread;
import backend.threads.initializers.RoutineThread;
import backend.threads.initializers.WorkerThread;
import backend.threads.initializers.provider.ProductInitializerThread;
import backend.threads.initializers.provider.ProvidingThread;
import backend.time.Time;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.view.VelocityViewServlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

public class Servlet extends VelocityViewServlet {

    public Template handleRequest(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Context context )
    {

        String p1 = "Jakarta";
        String p2 = "Velocity";

        Vector vec = new Vector();
        vec.addElement( p1 );
        vec.addElement( p2 );

        context.put("list", vec );

        Template template = null;

        try
        {
            template =  getTemplate("sample.vm");
        }
        catch( ResourceNotFoundException rnfe )
        {
            // couldn't find the template
        }
        catch( ParseErrorException pee )
        {
            // syntax error : problem parsing the template
        }
        catch( Exception e )
        {}

        return template;
    }
}