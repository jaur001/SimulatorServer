package backend.implementations.workerSearcher.strategy.strategies.complexStrategy.strategies;

import backend.implementations.workerSearcher.strategy.strategies.complexStrategy.ComplexWorkerStrategy;
import backend.model.simulables.person.worker.Worker;


public class LowestSalaryStrategy extends ComplexWorkerStrategy {

    @Override
    public Worker getBetterWorker(Worker worker1, Worker worker2) {
        if(desireSameSalary(worker1, worker2)) return new BestProportionScoreSalaryStrategy().getBetterWorker(worker1,worker2);
        return worker1.getSalaryDesired()<=worker2.getSalaryDesired()? worker1 : worker2;
    }

    public boolean desireSameSalary(Worker worker1, Worker worker2) {
        return worker1.getSalaryDesired()==worker2.getSalaryDesired();
    }
}
