package backend.view.loaders.database.builder;

import backend.model.bill.generator.XMLBill;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.view.loaders.database.builder.builders.*;

import java.util.HashMap;
import java.util.Map;

public class BuilderController {

    private static final Map<Class,Builder> builderTable = new HashMap<>();

    static {
        builderTable.put(XMLBill.class, new BillBuilder());
        builderTable.put(Client.class, new ClientBuilder());
        builderTable.put(Restaurant.class, new RestaurantBuilder());
        builderTable.put(Provider.class, new ProviderBuilder());
        builderTable.put(ServiceCompany.class, new ServiceCompanyBuilder());
        builderTable.put(Worker.class, new WorkerBuilder());
    }

    public static Builder getBuilder(Class object){
        return builderTable.get(object);
    }
}
