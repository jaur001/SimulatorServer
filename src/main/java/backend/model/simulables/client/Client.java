package backend.model.simulables.client;

import backend.implementations.routine.DistributionAmountGenerator;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.simulables.client.routineList.RoutineList;
import backend.model.simulables.restaurant.Bill;
import backend.model.bill.bills.EatingSale;
import backend.model.simulables.restaurant.Restaurant;
import backend.model.simulables.Simulable;
import backend.model.simulation.TimeLine;
import backend.utils.BillsUtils;

import java.util.Date;

public class Client implements Simulable {
    PersonalData personalData;

    private RoutineList routineList;
    private int peopleInvited;


    public Client(String[] data) {
        peopleInvited = 0;
        this.personalData = new PersonalData(data);
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
        peopleInvited = BillsUtils.getPeopleInvitedSample();
    }

    public void printRoutines(){
        System.out.print("Client: "+ this.getLastName() + " ->    ");
        this.routineList.printCount();
    }

    public int getNIF() {
        return personalData.getNIF();
    }

    public String getFirstName() {
        return personalData.getFirstName();
    }

    public String getLastName() {
        return personalData.getLastName();
    }

    public String getFullName(){
        return personalData.getFirstName() + " " + personalData.getLastName();
    }

    public String getJob() {
        return personalData.getJob();
    }

    public String getCountry() {
        return personalData.getCountry();
    }

    public double getSalary(){
        return routineList.getSalary();
    }

    public double getSalarySpent(){
        return routineList.getSalary();
    }

    public String getTelephoneNumber() {
        return personalData.getTelephoneNumber();
    }

    public String getCardNumber() {
        return personalData.getCardNumber();
    }

    public String getEmail() {
        return personalData.getEmail();
    }

    public String getGender() {
        return personalData.getGender();
    }

    public Date getBirthDate() {
        return personalData.getBirthDate();
    }

    @Override
    public void simulate() {
        routineList.checkRoutines().stream().limit(2).forEach(this::goToEat);
        if(TimeLine.isLastDay()) routineList.restartBudget();
        //this.printRoutines();
    }

    private void goToEat(Restaurant restaurant) {
        if(!restaurant.acceptClient(this)) return;
        double amount = new DistributionAmountGenerator().generate(restaurant,this);
        this.pay(amount,restaurant);
        new CFDIBillGenerator().generateBill(new EatingSale(restaurant,this,new Bill(amount), peopleInvited));
    }
}
