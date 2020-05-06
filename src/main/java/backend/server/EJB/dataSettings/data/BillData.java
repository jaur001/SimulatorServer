package backend.server.EJB.dataSettings.data;

public class BillData {

    private int plateNumberMean;
    private double plateNumberSd;

    public BillData(int plateNumberMean, double plateNumberSd) {
        this.plateNumberMean = plateNumberMean;
        this.plateNumberSd = plateNumberSd;
    }

    public int getPlateNumberMean() {
        return plateNumberMean;
    }

    public double getPlateNumberSd() {
        return plateNumberSd;
    }
}
