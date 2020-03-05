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


    public void addDebt(double productPrice) {
        purchases+=productPrice;
        totalPassive+=productPrice;
    }

    public void removeDebt(double productPrice) {
        purchases-=productPrice;
        totalPassive-=productPrice;
    }

    public void payDebts(){
        reset();
        treasury+= totalActive - totalPassive;
        netWorth+= totalActive - totalPassive;
        System.out.println("Treasury: " + treasury + "$");
        System.out.println("netWorth: " + netWorth + "$");
        System.out.println("Last Month Data: ");
        System.out.println("Treasury: " + lastMonthdata.treasury + "$");
        System.out.println("netWorth: " + lastMonthdata.netWorth + "$");
        totalActive = 0;
    }
}
