package backend.model.simulables.person.client;

import backend.implementations.routine.DistributionAmountGenerator;
import backend.model.bill.bills.EatingSale;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.event.EventGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.Collector;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.company.restaurant.EatingBill;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulation.settings.settingsList.ClientSettings;


public class Client extends EventGenerator implements Simulable,EconomicAgent, Collector {
    protected PersonalData personalData;
    protected RoutineList routineList;
    protected int peopleInvited;

    public Client(PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
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

    public void setJob(String job){
        personalData.setJob(job);
    }

    public String getCountry() {
        return personalData.getCountry();
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

    public int getAge(){
        return personalData.getAge();
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

    protected void invitePeople(){
        peopleInvited = ClientSettings.getPeopleInvitedSample();
    }

    public void printRoutines(){
        System.out.print("Client: "+ this.getLastName() + " ->    ");
        this.routineList.printCount();
    }

    public double getSalary(){
        return personalData.getSalary();
    }

    public void setSalary(double salary) {
        personalData.setSalary(salary);
    }

    public double getSalarySpent(){
        return routineList.getBudget();
    }


    @Override
    public void simulate() {
        routineList.checkRoutines().forEach(this::goToEat);
        //this.printRoutines();
    }

    protected void goToEat(Restaurant restaurant) {
        if(!restaurant.acceptClient(this)) return;
        this.invitePeople();
        addEvent(payRestaurant(restaurant));
    }

    protected EatingSale payRestaurant(Restaurant restaurant) {
        double amount = new DistributionAmountGenerator().generate(restaurant,this);
        EatingSale bill = new EatingSale(restaurant, this, new EatingBill(amount));
        Bank.makeTransaction(this,restaurant,amount);
        new CFDIBillGenerator().generateBill(bill);
        return bill;
    }


    @Override
    public void pay(double amount) {
        this.getRoutineList().decreaseBudget(amount);
    }

    @Override
    public void collect(double amount) {
        this.getRoutineList().increaseBudget(amount);
    }

    @Override
    public void collect() {
        routineList.restartBudget();
    }
}
