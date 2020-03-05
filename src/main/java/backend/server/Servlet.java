package backend.server;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.view.VelocityViewServlet;
import java.util.Vector;

public class Servlet extends VelocityViewServlet {

    public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context ) {

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