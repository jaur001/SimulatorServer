package backend.server.frontcontroller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            return (FrontCommand) getCommandClass(request).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class getCommandClass(HttpServletRequest request) {
        try {
            return Class.forName("backend.server.frontcontroller." + request.getParameter("command"));
        } catch (ClassNotFoundException e) {
            return UnknownCommand.class;
        }
    }
}
