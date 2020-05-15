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

    public void setPlateNumberMean(int plateNumberMean) {
        this.plateNumberMean = plateNumberMean;
    }

    public void setPlateNumberSd(double plateNumberSd) {
        this.plateNumberSd = plateNumberSd;
    }
}
