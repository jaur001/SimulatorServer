package backend.model.simulables.person.client;

import backend.implementations.routine.DistributionAmountGenerator;
import backend.model.bill.generator.CFDIBillGenerator;
import backend.model.event.Event;
import backend.model.event.EventFactory;
import backend.model.event.events.EatingSaleEvent;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.Collector;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.person.Person;
import backend.model.simulables.person.PersonalData;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulables.company.restaurant.EatingBill;
import backend.model.bill.bills.EatingSale;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.Simulable;
import backend.model.simulation.TimeLine;
import backend.model.simulation.settings.settingsList.ClientSettings;

public class Client extends Person implements Simulable, EventFactory, EconomicAgent, Collector {
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
        return routineList.getBudget();
    }


    @Override
    public void simulate() {
        routineList.checkRoutines().forEach(this::goToEat);
        //this.printRoutines();
    }

    private void goToEat(Restaurant restaurant) {
        if(!restaurant.acceptClient(this)) return;
        this.invitePeople();
        EatingSale bill = payRestaurant(restaurant);
        EventFactory.addEvent(buildEvent(bill));
    }

    private EatingSale payRestaurant(Restaurant restaurant) {
        double amount = new DistributionAmountGenerator().generate(restaurant,this);
        EatingSale bill = new EatingSale(restaurant, this, new EatingBill(amount));
        Bank.makeTransaction(this,restaurant,amount);
        new CFDIBillGenerator().generateBill(bill);
        return bill;
    }

    @Override
    public Event buildEvent(Object data) {
        return new EatingSaleEvent((EatingSale) data);
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
