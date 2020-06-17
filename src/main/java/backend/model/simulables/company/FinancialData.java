package backend.model.simulables.company;

import backend.model.simulables.company.complexCompany.ComplexCompany;
import backend.model.simulables.person.worker.Worker;

import java.util.LinkedHashMap;
import java.util.Map;

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
    private Map<ComplexCompany,Double> debtsTable = new LinkedHashMap<>();
    private Map<Worker,Double> payrolls = new LinkedHashMap<>();


    public FinancialData(double socialCapital) {
        this.socialCapital = socialCapital;
        this.treasury = socialCapital;
        this.netWorth = socialCapital;
        this.totalActive = 0;
        this.totalPassive = 0;
        this.lastMonthData = null;
        reset();
    }

    public Map<ComplexCompany, Double> getDebtsTable() {
        return debtsTable;
    }

    public Map<Worker, Double> getPayrolls() {
        return payrolls;
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

    public void addDebt(ComplexCompany company, double amount) {
        totalPassive+=amount;
        debtsTable.put(company,amount);
    }

    public void removeDebt(ComplexCompany company) {
        if(debtsTable.containsKey(company)){
            totalPassive-=debtsTable.get(company);
            debtsTable.remove(company);
        }
    }

    public void addDebt(Worker worker, double amount) {
        totalPassive+=amount;
        payrolls.put(worker,amount);
    }

    public void removeDebt(Worker worker) {
        if(payrolls.containsKey(worker)){
            totalPassive-=payrolls.get(worker);
            payrolls.remove(worker);
        }
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

    public double getNetWorth() {
        return netWorth;
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
