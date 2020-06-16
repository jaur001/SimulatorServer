package backend.server.EJB;

import backend.model.bill.generator.XMLBill;

import java.util.LinkedList;
import java.util.List;

public class BillDataSingletonBean {
    private List<XMLBill> billList;

    public BillDataSingletonBean() {
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
