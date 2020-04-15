package backend.model.simulation.administration;

import backend.model.event.EventGenerator;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

public class SimulationCommitter extends EventGenerator {


    public void commitAddProvider(Restaurant restaurant, Provider provider){
        restaurant.getProviders().add(provider);
        restaurant.getFinancialData().addDebt(provider.getProductPrice());
        provider.addClient(restaurant);
    }

    public void commitRemoveProvider(Restaurant restaurant, Provider provider){
        if(restaurant.getProviders().contains(provider)){
            restaurant.getProviders().remove(provider);
            restaurant.getFinancialData().removeDebt(provider.getProductPrice());
            provider.removeClient(restaurant);
        }
    }

    public void commitAddWorker(Restaurant restaurant, Worker worker){
        worker.hire(restaurant, RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
        restaurant.getFinancialData().addDebt(worker.getSalaryDesired());
        addEvent(worker);
    }

    public void commitRemoveWorker(Restaurant restaurant, Worker worker){
        if(restaurant.getWorkers().contains(worker)){
            worker.fire();
            restaurant.getFinancialData().removeDebt(worker.getSalary());
            addEvent(worker);
        }
    }

    public void commitRetireWorker(Restaurant restaurant, Worker worker) {
        if(restaurant.getWorkers().contains(worker)){
            worker.retire();
            Simulation.getWorkerList().remove(worker);
            restaurant.getFinancialData().removeDebt(worker.getSalary());
            addEvent(worker);
        }
    }
}
