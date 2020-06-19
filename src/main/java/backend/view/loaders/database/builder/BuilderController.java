package backend.view.loaders.database.builder;

import backend.model.bill.generator.XMLBill;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.provider.Provider;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsData.data.*;
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
        builderTable.put(GeneralData.class, new GeneralDataBuilder());
        builderTable.put(ClientData.class, new ClientDataBuilder());
        builderTable.put(RestaurantData.class, new RestaurantDataBuilder());
        builderTable.put(ProviderData.class, new ProviderDataBuilder());
        builderTable.put(ServiceData.class, new ServiceDataBuilder());
        builderTable.put(WorkerData.class, new WorkerDataBuilder());
    }

    public static Builder getBuilder(Class object){
        return builderTable.get(object);
    }
}
