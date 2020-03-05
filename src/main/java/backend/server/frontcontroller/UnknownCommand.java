package backend.server.frontcontroller;

public class UnknownCommand extends FrontCommand{
    @Override
    public void process() {
        forward("/unknown.jsp");
    }
}
