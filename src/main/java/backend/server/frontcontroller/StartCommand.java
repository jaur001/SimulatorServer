package backend.server.frontcontroller;

import backend.main.Main;

public class StartCommand extends FrontCommand {
    @Override
    public void process() {
        Main.main(null);
        forward("/index.jsp");
    }
}
