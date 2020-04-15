package backend.initializers.secondaryCompanies.provider;

import backend.implementations.secondaryCompanies.providing.RandomProductInitializer;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Product;
import backend.model.simulables.company.secondaryCompany.companies.monthlyCompanies.provider.Provider;

import java.util.List;
import java.util.stream.IntStream;

public class ProductThread {


    public static void initProducts(List<Provider> providerList) {
        providerList.parallelStream().forEach(ProductThread::initProduct);
        if(providerList.size()>=Product.values().length)initOneProviderPerProduct(providerList);
    }

    private static void initProduct(Provider provider) {
        provider.setProduct(new RandomProductInitializer().init());
    }

    private static void initOneProviderPerProduct(List<Provider> providerList) {
        IntStream.range(0,Product.values().length).boxed()
                .forEach(integer -> {
                    providerList.get(integer).setProduct(Product.values()[integer]);
                });
    }
}

