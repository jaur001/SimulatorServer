package backend.server.EJB;

import backend.model.bill.generator.XMLBill;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton(name = "BillDataSingletonEJB")
public class BillDataSingletonBean {
    private List<XMLBill> billList;

    public BillDataSingletonBean() {
        reset();
    }

    @PostConstruct
    public void init(){
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
