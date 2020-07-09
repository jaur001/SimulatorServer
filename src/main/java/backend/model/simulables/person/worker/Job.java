package backend.model.simulables.person.worker;

public enum Job {
    Waiter,
    Kitchen_Helper,
    Cook,
    Maitre,
    Chef;

    @Override
    public String toString() {
        return this.name();
    }

}

