package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.GenericDataSettings;
import backend.server.EJB.dataSettings.data.BillData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "BillSettingsStatefulEJB")
public class BillSettingsStatefulBean extends GenericDataSettings {

    private int plateNumberMean;
    private double plateNumberSD;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof BillData){
            BillData billData = (BillData) data;
            plateNumberMean = billData.getPlateNumberMean();
            plateNumberSD = billData.getPlateNumberSd();
        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultBillSettings());
    }

    public int getPlateNumberMean() {
        return plateNumberMean;
    }

    public double getPlateNumberSD() {
        return plateNumberSD;
    }

    public BillData getBillData() {
        return new BillData(plateNumberMean,plateNumberSD);
    }
}
