package backend.model.simulation.settings.data;

public class GeneralData {
    private int clientCount;
    private int restaurantCount;
    private int providerCount;

    public GeneralData(int clientCount, int restaurantCount, int providerCount) {
        this.clientCount = clientCount;
        this.restaurantCount = restaurantCount;
        this.providerCount = providerCount;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getRestaurantCount() {
        return restaurantCount;
    }

    public int getProviderCount() {
        return providerCount;
    }
}
