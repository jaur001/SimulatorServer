package backend.server.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response){
        this.context = context;
        this.request = request;
        this.response = response;
    }
    abstract public void process();
    public void forward(String target){
        try {
            context.getRequestDispatcher(target).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected int getIntParameter(String name){
        return Integer.parseInt(request.getParameter(name));
    }

    protected double getDoubleParameter(String name){
        return Double.parseDouble(request.getParameter(name));
    }

    protected String getStringParameter(String name){
        return request.getParameter(name);
    }

    protected void setToRequest(String name, Object value) {
        request.setAttribute(name, value);
    }
}
