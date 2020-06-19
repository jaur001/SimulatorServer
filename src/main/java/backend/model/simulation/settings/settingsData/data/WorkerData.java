package backend.model.simulation.settings.settingsData.data;

public class WorkerData {

    private double minSalary;
    private double salaryChange;
    private double salaryDesiredChange;
    private int retireAge;
    private double percentageRetirement;

    public WorkerData(double minSalary, double salaryChange, double salaryDesiredChange, int retireAge, double percentageRetirement) {
        this.minSalary = minSalary;
        this.salaryChange = salaryChange;
        this.salaryDesiredChange = salaryDesiredChange;
        this.retireAge = retireAge;
        this.percentageRetirement = percentageRetirement;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public double getSalaryChange() {
        return salaryChange;
    }

    public double getSalaryDesiredChange() {
        return salaryDesiredChange;
    }

    public int getRetireAge() {
        return retireAge;
    }

    public double getPercentageRetirement() {
        return percentageRetirement;
    }
}
