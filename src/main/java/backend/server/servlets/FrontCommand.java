package backend.server.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    protected int getAbsoluteIntParameter(String name){
        return Math.abs(Integer.parseInt(request.getParameter(name)));
    }

    protected double getAbsoluteDoubleParameter(String name){
        return Math.abs(Double.parseDouble(request.getParameter(name)));
    }

    protected void setToRequest(String name, Object value) {
        request.setAttribute(name, value);
    }

    protected void setToSession(String name, Object value) {
        request.getSession(true).setAttribute(name, value);
    }

    protected HttpSession getSession(){
        return request.getSession(true);
    }
}
