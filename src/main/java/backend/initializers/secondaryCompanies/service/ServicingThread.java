package backend.initializers.secondaryCompanies.service;

import backend.implementations.secondaryCompanies.service.RandomServicingInitializer;
import backend.model.simulables.company.Company;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.Service;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.service.ServiceCompany;
import backend.model.simulation.administration.Simulation;

import java.util.List;

public class ServicingThread {

    public static void initRestaurantsWithCleaning(){
        Simulation.getRestaurantListCopy().forEach((restaurant -> initCompanyWithService(restaurant,Service.Cleaning)));
    }

    public static void initProvidersWithTransport(){
        Simulation.getProviderListCopy().forEach((provider -> initCompanyWithService(provider,Service.Transport)));
    }

    public static void initCompaniesWithService(List<Company> companyList, Service service){
        companyList.parallelStream().forEach(company -> initCompanyWithService(company,service));
    }


    public static void initCompanyWithService(Company company, Service service) {
        ServiceCompany serviceCompany = new RandomServicingInitializer().provide(service);
        company.addService(serviceCompany);
        serviceCompany.addClient(company);
    }
}
