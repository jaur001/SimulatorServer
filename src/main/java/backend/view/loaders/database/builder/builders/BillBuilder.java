package backend.view.loaders.database.builder.builders;


import backend.model.bill.Type;
import backend.model.bill.generator.XMLBill;
import backend.view.loaders.database.builder.Builder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BillBuilder extends Builder<XMLBill> {

    @Override
    protected List<Object> getRow(XMLBill bill) {
        return Arrays.asList(new Object[]{bill.getUUID(),bill.getStreet(),bill.getType()
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
        bill.setType((Type) parameters[2]);
        bill.setIssuerName((String)parameters[3]);
        bill.setIssuerRFC((int)parameters[4]);
        bill.setReceiverName((String)parameters[5]);
        bill.setReceiverRFC((int) parameters[6]);
        bill.setTotal((double)parameters[7]);
        bill.setTaxRate((double)parameters[8]);
        bill.setSubtotal((double)parameters[9]);
        bill.setCurrency((String)parameters[10]);
        bill.setConcept((String) parameters[11]);
        bill.setDate((Date)parameters[12]);
        bill.setFilePath((String) parameters[13]);
        bill.setFileName((String)parameters[14]);
    }
}
