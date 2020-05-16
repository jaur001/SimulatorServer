package backend.server.EJB.dataSettings.dataSettingsEJB;

import backend.server.EJB.dataSettings.DistributionData;
import backend.server.EJB.dataSettings.GenericDataSettings;
import backend.server.EJB.dataSettings.data.BillData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "BillSettingsStatefulEJB")
public class BillSettingsStatefulBean extends GenericDataSettings {

    private BillData data;

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof BillData){
            this.data = (BillData) data;
        }
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultBillSettings());
    }

    public DistributionData getPlateNumber(){
        return data.getPlateNumber();
    }

    public BillData getBillData() {
        return data;
    }
}
