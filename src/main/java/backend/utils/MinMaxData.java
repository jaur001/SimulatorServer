package backend.utils;

public class MinMaxData {
    private int min;
    private int max;

    public MinMaxData(int min, int max) {
        if(min>max) init(max,min);
        else init(min, max);
    }

    public void init(int min, int max) {
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
