package backend.server.frontcontroller;

import backend.model.provider.Provider;

import java.util.ArrayList;
import java.util.List;

public class ProvidersCommand extends FrontCommand{

    @Override
    public void process() {
        List<Provider> providerList = new ArrayList<>();
        providerList.add(new Provider());
        providerList.add(new Provider());
        providerList.add(new Provider());
        request.setAttribute("providerList",providerList);
        forward("/providers.jsp");
    }
}
