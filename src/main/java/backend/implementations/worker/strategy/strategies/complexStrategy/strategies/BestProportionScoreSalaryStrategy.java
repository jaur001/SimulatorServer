package backend.implementations.worker.strategy.strategies.complexStrategy.strategies;

import backend.implementations.worker.strategy.strategies.complexStrategy.ComplexWorkerStrategy;
import backend.model.simulables.person.worker.Worker;
import backend.model.simulation.settings.settingsList.RestaurantSettings;

public class BestProportionScoreSalaryStrategy extends ComplexWorkerStrategy {
    @Override
    public Worker getBetterWorker(Worker worker1, Worker worker2) {
        return RestaurantSettings.getSalaryPerQuality(worker1) >= RestaurantSettings.getSalaryPerQuality(worker2)? worker1: worker2;
    }



}
