package backend.server.EJB.dataSettings.data;

public class GeneralData {
    private int clientCount;
    private int restaurantCount;
    private int providerCount;
    private int serviceCount;
    private int workerCount;

    public GeneralData(int clientCount, int restaurantCount, int providerCount, int serviceCount, int workerCount) {
        this.clientCount = clientCount;
        this.restaurantCount = restaurantCount;
        this.providerCount = providerCount;
        this.serviceCount = serviceCount;
        this.workerCount = workerCount;
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

    public int getServiceCount() {
        return serviceCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }
}
