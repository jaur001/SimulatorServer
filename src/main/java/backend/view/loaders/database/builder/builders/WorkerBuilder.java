package backend.view.loaders.database.builder.builders;

import backend.model.simulables.person.PersonalData;
import backend.model.simulables.person.client.Client;
import backend.model.simulables.person.worker.Worker;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.List;

public class WorkerBuilder extends Builder<Worker> {
    @Override
    protected List<Object> getRow(Worker worker) {
        return Arrays.asList(new Object[]{worker.getNIF()
                ,worker.getFirstName(),worker.getLastName(),worker.getBirthDate()
                ,worker.getGender(),worker.getJob(),worker.getCountry()
                ,worker.getTelephoneNumber(),worker.getEmail(),worker.getCardNumber()});
    }

    @Override
    protected Worker getItem(Object[] parameters) {
        return new Worker(new PersonalData((int)parameters[0]
                ,(String) parameters[1],(String) parameters[2],(String)parameters[3]
                ,(String) parameters[4],(String) parameters[5],(String) parameters[6]
                ,(String) parameters[7],(String) parameters[8],(String) parameters[9]));
    }
}
