package backend.model.event.events.client;

import backend.model.event.GenericEvent;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;

public class NewClientEvent extends GenericEvent<Client> {

    public NewClientEvent(Client client) {
        super(client);
    }

    @Override
    public String getMessage() {
        return  super.simulable.getFullName() + " has entered to the simulation.";
    }
}
