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
import backend.utils.RoutineUtils;

import java.util.Date;

public class Client implements Simulable {
    PersonalData personalData;

    private RoutineList routineList;
    private int peopleInvited;

    public Client(int NIF,String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        personalData = new PersonalData(NIF,firstName,lastName,birthDate,gender,job,country,telephoneNumber,email,cardNumber);
    }

    public Client(String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        personalData = new PersonalData(firstName,lastName,birthDate,gender,job,country,telephoneNumber,email,cardNumber);
    }

    public Client(PersonalData personalData) {
        this.personalData = personalData;
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
        peopleInvited = RoutineUtils.getPeopleInvitedSample();
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
        return routineList.getSalarySpent();
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

    public String getBirthDate() {
        return personalData.getBirthDate();
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
        new CFDIBillGenerator().generateBill(new EatingSale(restaurant,this,new Bill(amount)),"EatingSale");
    }
}
