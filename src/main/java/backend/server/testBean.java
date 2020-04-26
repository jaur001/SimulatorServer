package backend.server;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
@Stateless(name = "testEJB")
public class testBean {
    public testBean() {

    }
}
