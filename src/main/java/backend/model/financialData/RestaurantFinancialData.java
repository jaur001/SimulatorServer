package backend.model.financialData;


public class RestaurantFinancialData extends FinancialData {



    public RestaurantFinancialData(double socialCapital) {
        super(socialCapital);
    }



    public void addSale(double amount){
        sales += amount;
        treasury += amount;
        netWorth += amount;
        totalActive += amount;
    }


    public void addDebt(double amount) {
        purchases+=amount;
        totalPassive+=amount;
    }

    public void removeDebt(double amount) {
        purchases-=amount;
        totalPassive-=amount;
    }

    public void payDebts(){
        reset();
        treasury+= totalActive - totalPassive;
        netWorth+= totalActive - totalPassive;
        totalActive = 0;
    }
}
