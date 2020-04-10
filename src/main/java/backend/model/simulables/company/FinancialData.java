package backend.model.simulables.company;

public class FinancialData implements Cloneable {
    /*
    beneficios, total del activo, total del pasivo, patrimonio neto, capital social, tesoreria, compras, ventas
     */
    private double totalActive;   // total del activo
    private double totalPassive;  // total del pasivo

    private double netWorth;      // patrimonio neto
    private double treasury;      // tesoreria
    private final double socialCapital; // capital social

    private double purchases;     // compras
    private double sales;         // ventas
    private FinancialData lastMonthData;


    public FinancialData(double socialCapital) {
        this.socialCapital = socialCapital;
        this.treasury = socialCapital;
        this.netWorth = socialCapital;
        this.totalActive = 0;
        this.totalPassive = 0;
        this.lastMonthData = null;
        reset();
    }

    public void addSale(double amount){
        sales += amount;
    }

    public void addPurchase(double amount){
        purchases += amount;
    }


    public void addDebt(double amount) {
        totalPassive+=amount;
    }

    public void removeDebt(double amount) {
        totalPassive-=amount;
    }

    public void addIncome(double amount) {
        totalActive+=amount;
    }

    public void removeIncome(double amount) {
        totalActive-=amount;
    }

    public void pay(double amount){
        treasury-= amount;
        netWorth -= amount;
    }

    public void collect(double amount){
        treasury+= amount;
        netWorth+= amount;
    }

    public FinancialData getLastMonthData(){
        return lastMonthData;
    }

    public void addMonthData(){
        try {
            this.lastMonthData = (FinancialData)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        addMonthData();
        this.purchases = 0;
        this.sales = 0;
    }

    public double getTreasury() {
        return treasury;
    }

    public double getTotalActive() {
        return totalActive;
    }


    public double getTotalPassive() {
        return totalPassive;
    }

    public double getSocialCapital() {
        return socialCapital;
    }

    public double getBenefits(){
        return getIncome()-getLosses();
    }

    public double getIncome(){
        return sales+totalActive;
    }

    public double getLosses(){
        return purchases+totalPassive;
    }

    public double getPurchases() {
        return purchases;
    }

    public double getSales() {
        return sales;
    }

    public double getLastMonthBenefits(){
        return lastMonthData.getBenefits();
    }
    public double getLastMonthIncome(){
        return lastMonthData.getIncome();
    }
    public double getLastMonthLosses(){
        return lastMonthData.getLosses();
    }
}
