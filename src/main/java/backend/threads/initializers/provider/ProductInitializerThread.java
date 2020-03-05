package backend.threads.initializers.provider;

import backend.implementations.loaders.provider.product.DistributionProductPriceInitializer;
import backend.implementations.loaders.provider.product.RandomProductInitializer;
import backend.model.provider.Provider;
import java.util.List;

public class ProductInitializerThread{


    public static void initProducts(List<Provider> providerList) {
        providerList.parallelStream().forEach(ProductInitializerThread::initProduct);
    }

    private static void initProduct(Provider provider) {
        provider.setProduct(new RandomProductInitializer().init());
        provider.setProductPrice(new DistributionProductPriceInitializer().getPrice(provider.getProduct()));
    }
}

