package backend.implementations.worker.strategy.strategies.complexStrategy.strategies;

import backend.implementations.worker.strategy.strategies.complexStrategy.ComplexWorkerStrategy;
import backend.model.simulables.person.worker.Worker;

public class BestWorkerStrategy extends ComplexWorkerStrategy {
    @Override
    public Worker getBetterWorker(Worker worker1, Worker worker2) {
        if(hasSameQuality(worker1, worker2)) return new BestProportionScoreSalaryStrategy().getBetterWorker(worker1,worker2);
        return worker1.getQuality().getScore()>=worker2.getQuality().getScore()? worker1 : worker2;
    }

    public boolean hasSameQuality(Worker worker1, Worker worker2) {
        return worker1.getQuality().getScore()==worker2.getQuality().getScore();
    }
}
