package backend.model.simulables.bank;


import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulation.timeLine.TimeLine;

import java.util.LinkedList;
import java.util.List;

public class Bank implements Simulable {

    private static List<Collector> collectorList = new LinkedList<>();
    private static List<Payer> payerList = new LinkedList<>();


    public static void makeTransaction(EconomicAgent transmitter, EconomicAgent receiver, double amount){
        transmitter.pay(amount);
        receiver.collect(amount);
    }

    public static void addCollector(Collector collector){
        if(!collectorList.contains(collector)) collectorList.add(collector);
    }

    public static void addPayer(Payer payer){
        if(!payerList.contains(payer)) payerList.add(payer);
    }

    public static void reset() {
        collectorList = new LinkedList<>();
        payerList = new LinkedList<>();
    }

    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        if(TimeLine.isLastDay()){
            collectorList.forEach(Collector::collectSalary);
            payerList.forEach(Payer::payTaxes);
        }
    }

}
