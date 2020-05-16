package backend.server.EJB.dataSettings.data;

import backend.server.EJB.dataSettings.DistributionData;

public class BillData {

    private DistributionData plateNumber;

    public BillData(DistributionData plateNumber) {
        this.plateNumber = plateNumber;
    }

    public DistributionData getPlateNumber() {
        return plateNumber;
    }
}
