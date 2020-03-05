package backend.model.financialData;

public class ProviderFinancialData extends FinancialData {



    public ProviderFinancialData(double socialCapital) {
        super(socialCapital);
    }


    public void addMonthClient(double amount){
        sales += amount;
        treasury += amount;
        netWorth += amount;
        totalActive += amount;
    }

    public void removeMonthClient(double amount){
        sales -= amount;
        treasury -= amount;
        netWorth -= amount;
        totalActive -= amount;
    }

}
