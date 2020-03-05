package backend.utils;

import backend.model.provider.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ProviderUtils {

    private static final Map<Product, Integer> productCostTable = new HashMap<>();

    static {
        Product[] products = {Product.Vegetable,Product.Meat,Product.Fish,Product.Wheat,
                Product.Egg,Product.Legume,Product.Fruit};
        Integer[] cost = {20,20,20,20,20,20,20};
        IntStream.range(0,cost.length).boxed()
                .forEach(i -> productCostTable.put(products[i],cost[i]));
    }

    public static int getProductCost(Product product){
        return productCostTable.get(product);
    }
}
