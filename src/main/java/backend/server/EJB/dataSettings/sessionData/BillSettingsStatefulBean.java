package backend.server.EJB.dataSettings.sessionData;

import backend.server.EJB.dataSettings.DistributionData;
import backend.server.EJB.dataSettings.GenericSessionDataSettings;
import backend.server.EJB.dataSettings.data.BillData;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful(name = "BillSettingsStatefulEJB")
public class BillSettingsStatefulBean extends GenericSessionDataSettings {

    private BillData data;

    public BillSettingsStatefulBean() {
        initSettings();
    }

    @PostConstruct
    public void initSettings(){
        initDefaultSettings();
        setDefault();
    }

    @Override
    public void init(Object data) {
        if(data instanceof BillData) this.data = (BillData) data;
    }

    @Override
    public void setDefault() {
        init(defaultSettings.getDefaultBillData());
    }         

    public BillData getBillData() {
        return data;
    }
}
