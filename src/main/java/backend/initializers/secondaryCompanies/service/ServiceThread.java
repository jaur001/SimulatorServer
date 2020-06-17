package backend.initializers.secondaryCompanies.service;

import backend.implementations.secondaryCompanies.service.RandomServiceInitializer;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.Service;
import backend.model.simulables.company.complexCompany.secondaryCompany.monthlyCompanies.service.ServiceCompany;

import java.util.List;
import java.util.stream.IntStream;

public class ServiceThread {

    public static void initProducts(List<ServiceCompany> companies) {
        companies.parallelStream().forEach(ServiceThread::initProduct);
        if(companies.size()>= Service.values().length) initOneCompanyPerService(companies);
    }

    private static void initProduct(ServiceCompany company) {
        company.setProduct(new RandomServiceInitializer().init());
    }

    private static void initOneCompanyPerService(List<ServiceCompany> companies) {
        IntStream.range(0, Service.values().length).boxed()
                .forEach(integer -> {
                    companies.get(integer).setProduct(Service.values()[integer]);
                });
    }
}
