package backend.model.simulables.person.client;

import backend.implementations.routine.DistributionAmountGenerator;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.person.Person;
import backend.model.simulables.person.PersonalData;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.restaurant.EatingBill;
import backend.model.bill.bills.EatingSale;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulables.Simulable;
import backend.model.simulation.TimeLine;
import backend.model.simulation.settings.settingsList.ClientSettings;

public class Client extends Person implements Simulable {
    PersonalData personalData;

    private RoutineList routineList;
    private int peopleInvited;

    public Client(int NIF,String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        super(new PersonalData(NIF,firstName,lastName,birthDate,gender,job,country,telephoneNumber,email,cardNumber));
    }

    public Client(String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        super(new PersonalData(firstName,lastName,birthDate,gender,job,country,telephoneNumber,email,cardNumber));
    }

    public Client(PersonalData personalData) {
        super(personalData);
    }

    public void pay(double amount, Restaurant restaurant){
        this.getRoutineList().decreaseBudget(amount);
        restaurant.payEating(amount);
    }

    public RoutineList getRoutineList() {
        return routineList;
    }

    public void setRoutineList(RoutineList routineList) {
        this.routineList = routineList;
    }

    public int getPeopleInvited() {
        return peopleInvited;
    }

    private void invitePeople(){
        peopleInvited = ClientSettings.getPeopleInvitedSample();
    }

    public void printRoutines(){
        System.out.print("Client: "+ this.getLastName() + " ->    ");
        this.routineList.printCount();
    }

    public double getSalary(){
        return routineList.getSalary();
    }

    public double getSalarySpent(){
        return routineList.getSalarySpent();
    }


    @Override
    public void simulate() {
        routineList.checkRoutines().forEach(this::goToEat);
        if(TimeLine.isLastDay()){
            routineList.restartBudget();
        }
        //this.printRoutines();
    }

    private void goToEat(Restaurant restaurant) {
        if(!restaurant.acceptClient(this)) return;
        this.invitePeople();
        double amount = new DistributionAmountGenerator().generate(restaurant,this);
        this.pay(amount,restaurant);
        new CFDIBillGenerator().generateBill(new EatingSale(restaurant,this,new EatingBill(amount)));
    }
}
