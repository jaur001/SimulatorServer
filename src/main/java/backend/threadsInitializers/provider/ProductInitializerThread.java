package backend.threadsInitializers.provider;

import backend.implementations.loaders.providing.product.DistributionProductPriceInitializer;
import backend.implementations.loaders.providing.product.RandomProductInitializer;
import backend.model.simulables.provider.Product;
import backend.model.simulables.provider.Provider;

import java.util.List;
import java.util.stream.IntStream;

public class ProductInitializerThread{


    public static void initProducts(List<Provider> providerList) {
        providerList.parallelStream().forEach(ProductInitializerThread::initProduct);
        initOneProviderPerProduct(providerList);
    }

    private static void initProduct(Provider provider) {
        provider.setProduct(new RandomProductInitializer().init());
        provider.setProductPrice(new DistributionProductPriceInitializer().getPrice(provider.getProduct()));
    }

    private static void initOneProviderPerProduct(List<Provider> providerList) {
        IntStream.range(0,Product.values().length).boxed()
                .forEach(integer -> {
                    providerList.get(integer).setProduct(Product.values()[integer]);
                    providerList.get(integer).setProductPrice(new DistributionProductPriceInitializer().getPrice(Product.values()[integer]));
                });
    }
}

