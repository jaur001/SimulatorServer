package backend.model.simulation.administration.data;

import backend.model.bill.generator.XMLBill;

import java.util.LinkedList;
import java.util.List;

public class SimulationBillData {
    private List<XMLBill> billList;

    public SimulationBillData() {
        reset();
    }


    public void addBill(XMLBill bill){
        billList.add(bill);
    }

    public List<XMLBill> getBillList(int from, int to) {
        return billList.subList(from,to);
    }

    public void reset(){
        billList = new LinkedList<>();
    }

    public int getSize(){
        return billList.size();
    }
}
