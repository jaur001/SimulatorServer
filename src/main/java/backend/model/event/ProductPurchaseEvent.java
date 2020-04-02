package backend.model.event;

import backend.model.bill.bills.ProductPurchase;

public class ProductPurchaseEvent implements Event {

    ProductPurchase productPurchase;

    public ProductPurchaseEvent(ProductPurchase productPurchase) {
        this.productPurchase = productPurchase;
    }

    @Override
    public String getMessage() {
        return productPurchase.getReceiverName() + " has bought " + productPurchase.getProvider().getProduct() + " to " + productPurchase.getIssuerName() + ", amount: " + productPurchase.getTotal();
    }
}
