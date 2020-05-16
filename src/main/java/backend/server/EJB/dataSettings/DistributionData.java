package backend.server.EJB.dataSettings;

public class DistributionData {
    private double mean;
    private double sd;

    public DistributionData(double mean, double sd) {
        this.mean = mean;
        this.sd = sd;
    }

    public double getMean() {
        return mean;
    }

    public double getSd() {
        return sd;
    }
}
