package backend.initializers.secondaryCompanies.service;

import backend.implementations.secondaryCompanies.service.RandomServicingInitializer;
import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.centralControl.Simulation;

public class ServicingThread {

    public static void initRestaurantsWithCleaning(){
        Simulation.getRestaurantListCopy().forEach((restaurant -> initCompanyWithService(restaurant,Service.Cleaning)));
    }

    public static void initProvidersWithTransport(){
        Simulation.getProviderListCopy().forEach((provider -> initCompanyWithService(provider,Service.Transport)));
    }

    public static void initCompanyWithService(ComplexCompany company, Service service) {
        ServiceCompany serviceCompany = new RandomServicingInitializer().provide(service);
        company.addService(serviceCompany);
        serviceCompany.addClient(company);
    }
}
