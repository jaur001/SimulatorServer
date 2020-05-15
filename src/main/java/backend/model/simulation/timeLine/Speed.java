package backend.model.simulation.timeLine;

public class Speed {

    private static final double MIN_TIMEOUT = 100;
    private static final double MAX_TIMEOUT = 5000;
    private double timeout;

    public Speed() {
        timeout = 100;
    }

    public int getTimeout() {
        return (int)timeout;
    }

    public int getSpeed() {
        return 100-(int)(((timeout-MIN_TIMEOUT)/MAX_TIMEOUT)*100.0);
    }

    public void setSpeed(int speed) {
        if(speed==100) timeout = MAX_TIMEOUT;
        else this.timeout = MIN_TIMEOUT + ((100-speed)*MAX_TIMEOUT)/100;
    }
}
