package backend.model.simulables;


public class SimulableTester {

    public static Simulable actualSimulable;
    public static int method;

    public static void changeSimulable(Simulable simulable){
        actualSimulable = simulable;
    }

    public static void changeMethod(int method){
        SimulableTester.method = method;
    }


}
