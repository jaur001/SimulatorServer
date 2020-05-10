package backend.server.servlets;

import backend.server.commands.UnknownCommand;
import backend.utils.FrontControllerUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
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
        String command = request.getParameter("command");
        try {
            return Class.forName("backend.server.commands."+ FrontControllerUtils.getFolder(command) + command);
        } catch (ClassNotFoundException e) {
            return UnknownCommand.class;
        }
    }
}
