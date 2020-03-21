package backend.view.loaders.database.builder.builders;

import backend.model.simulables.client.Client;
import backend.model.simulables.client.PersonalData;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class ClientBuilder extends Builder<Client> {

    @Override
    protected List<Object> getRow(Client client){
        return Arrays.asList(new Object[]{client.getNIF()
                ,client.getFirstName(),client.getLastName()
                ,client.getEmail(),client.getGender()
                ,client.getBirthDate(),client.getJob()
                ,client.getCountry(),client.getTelephoneNumber()
                ,client.getCardNumber()});
    }

    @Override
    protected Client getItem(Object[] parameters) {
        return new Client(new PersonalData((int)parameters[0],(String) parameters[1],(String) parameters[2],(String)parameters[3]
                ,(String) parameters[4],(String) parameters[5],(String) parameters[6]
                ,(String) parameters[7],(String) parameters[8],(String) parameters[9]));

    }
}