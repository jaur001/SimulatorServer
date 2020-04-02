package backend.model.simulables.person.worker;

public enum Job {
    Waiter,
    KitchenHelper,
    Cooker,
    Maitre,
    Chef;

    @Override
    public String toString() {
        return this.name();
    }
}

