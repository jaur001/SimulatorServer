package backend.model.financialData;

public class FinancialData implements Cloneable {
    /*
    beneficios, total del activo, total del pasivo, patrimonio neto, capital social, tesoreria, compras, ventas
     */
    protected double totalActive;   // total del activo
    protected double totalPassive;  // total del pasivo

    protected double netWorth;      // patrimonio neto
    protected double treasury;      // tesoreria
    protected double socialCapital; // capital social

    protected double purchases;     // compras
    protected double sales;         // ventas
    protected FinancialData lastMonthdata;


    public FinancialData(double socialCapital) {
        this.socialCapital = socialCapital;
        this.treasury = socialCapital;
        this.netWorth = socialCapital;
        this.sales = 0;
        this.purchases = 0;
        this.totalPassive = 0;
        this.totalActive = 0;
        this.lastMonthdata = null;
    }

    public FinancialData getLastMonthData(){
        return lastMonthdata;
    }

    public void addMonthData(){
        try {
            this.lastMonthdata = (FinancialData)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    protected void reset() {
        addMonthData();
    }

    public double getBenefits() {
        return getTotalActive()- getTotalPassive();
    }


    public double getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(double totalActive) {
        this.totalActive += totalActive;
    }

    public double getTotalPassive() {
        return totalPassive;
    }

    public void setTotalPassive(double totalPassive) {
        this.totalPassive += totalPassive;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth += netWorth;
    }

    public double getSocialCapital() {
        return socialCapital;
    }

    public void setSocialCapital(double socialCapital) {
        this.socialCapital += socialCapital;
    }

    public double getTreasury() {
        return treasury;
    }

    public void setTreasury(double treasury) {
        this.treasury += treasury;
    }

    public double getPurchases() {
        return purchases;
    }

    public void setPurchases(double purchases) {
        this.purchases += purchases;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales += sales;
    }

    public void setWorkerSalary(double amount,int workersNumber){
        totalPassive += amount*workersNumber;
    }

    public void setRent(double amount){
        totalPassive += amount;
    }
}
