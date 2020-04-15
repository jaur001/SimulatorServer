package backend.model.event.events.client;

import backend.model.event.GenericEvent;
import backend.model.simulables.person.client.Client;

public class DeadClientEvent extends GenericEvent<Client> {

    public DeadClientEvent(Client client) {
        super(client);
    }

    @Override
    public String getMessage() {
        return simulable.getFullName() + " has died.";
    }
}
