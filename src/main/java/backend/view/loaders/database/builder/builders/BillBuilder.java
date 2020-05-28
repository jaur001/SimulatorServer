package backend.view.loaders.database.builder.builders;


import backend.model.NIFCreator.BillNIFCreator;
import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.bill.Type;
import backend.model.bill.Use;
import backend.model.bill.generator.XMLBill;
import backend.model.simulation.administration.centralControl.Simulation;
import backend.view.loaders.database.builder.Builder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class BillBuilder extends Builder<XMLBill> {

    @Override
    public String getHeader() {
        return "Bill";
    }

    @Override
    protected List<Object> getRow(XMLBill bill) {
        return Arrays.asList(new Object[]{bill.getUUID(),bill.getStreet(),bill.getType(),bill.getUse()
                ,bill.getIssuerName(),bill.getIssuerRFC(),bill.getReceiverName()
                ,bill.getReceiverRFC(),bill.getTotal(),bill.getTaxRate()
                ,bill.getSubtotal(),bill.getCurrency(),bill.getConcept()
                ,bill.getDate(),bill.getFilePath(),bill.getFileName()});
    }

    @Override
    protected XMLBill getItem(Object[] parameters) {
        XMLBill bill = new XMLBill();
        buildItem(parameters,bill);
        return bill;
    }

    private void buildItem(Object[] parameters, XMLBill bill) {
        bill.setUUID((int)parameters[0]);
        bill.setStreet((String)parameters[1]);
        bill.setType(Type.createType(parameters[2]));
        bill.setUse(Use.createUse(parameters[3]));
        bill.setIssuerName((String)parameters[4]);
        bill.setIssuerRFC((int)parameters[5]);
        bill.setReceiverName((String)parameters[6]);
        bill.setReceiverRFC((int) parameters[7]);
        bill.setTotal((double)parameters[8]);
        bill.setTaxRate((double)parameters[9]);
        bill.setSubtotal((double)parameters[10]);
        bill.setCurrency((String)parameters[11]);
        bill.setConcept((String) parameters[12]);
        bill.setDate((String) parameters[13]);
        bill.setFilePath((String) parameters[14]);
        bill.setFileName((String)parameters[15]);
    }
}
