package backend.model.simulables.restaurant.worker;

public enum Job {
    Waiter,
    KitchenHelper,
    Cooker,
    Maitre,
    Chef,
    Receptionist;

    @Override
    public String toString() {
        return this.name();
    }
}

