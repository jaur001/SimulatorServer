package backend.server.EJB.dataSettings;

public class MinMaxData {
    private int min;
    private int max;

    public MinMaxData(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
