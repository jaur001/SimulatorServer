package backend.model.simulation.administration;

import backend.model.event.EventController;
import backend.model.simulables.company.provider.Provider;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.worker.Job;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

public class SimulationCommitter {


    public static void commitAddProvider(Restaurant restaurant, Provider provider){
        restaurant.getProviders().add(provider);
        restaurant.getFinancialData().addDebt(provider.getProductPrice());
        provider.addClient(restaurant);
    }

    public static void commitRemoveProvider(Restaurant restaurant, Provider provider){
        if(restaurant.getProviders().contains(provider)){
            restaurant.getProviders().remove(provider);
            restaurant.getFinancialData().removeDebt(provider.getProductPrice());
            provider.removeClient(restaurant);
        }
    }

    public static void commitAddWorker(Restaurant restaurant, Worker worker){
        worker.hire(restaurant, RestaurantSettings.getSalary(Job.valueOf(worker.getJob())));
        restaurant.getFinancialData().addDebt(worker.getSalaryDesired());
        EventController.addEvent(worker);
    }

    public static void commitRemoveWorker(Restaurant restaurant, Worker worker){
        if(restaurant.getWorkers().contains(worker)){
            worker.fire();
            restaurant.getFinancialData().removeDebt(worker.getSalary());
            EventController.addEvent(worker);
        }
    }

    public static void commitRetireWorker(Restaurant restaurant, Worker worker) {
        if(restaurant.getWorkers().contains(worker)){
            worker.retire();
            Simulation.getWorkerList().remove(worker);
            restaurant.getFinancialData().removeDebt(worker.getSalary());
            EventController.addEvent(worker);
        }
    }
}
