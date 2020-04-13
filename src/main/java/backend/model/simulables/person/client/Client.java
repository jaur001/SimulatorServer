package backend.model.simulables.person.client;

import backend.implementations.routine.DistributionAmountGenerator;
import backend.model.event.Event;
import backend.model.simulables.Simulable;
import backend.model.simulables.SimulableTester;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.Collector;
import backend.model.simulables.bank.EconomicAgent;
import backend.model.simulables.bank.transactions.EatingSaleTransaction;
import backend.model.simulables.company.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulation.Simulation;
import backend.model.simulation.simulator.Simulator;
import backend.model.simulation.settings.settingsList.ClientSettings;



public class Client implements Simulable,EconomicAgent, Collector, Event {
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
        if(routineList!=null)routineList.setSalary(salary);
        personalData.setSalary(salary);
    }

    public double getSalarySpent(){
        return routineList.getBudget();
    }


    @Override
    public void simulate() {
        SimulableTester.changeSimulable(this);
        doClientsThings();
    }

    protected void doClientsThings() {
        if(ClientSettings.isGoingToDie(this.getAge())) {
            Simulator.isGoingToDie(this);
            return;
        }
        routineList.checkRoutines().forEach(this::goToEat);
        //this.printRoutines();
    }

    protected void goToEat(Restaurant restaurant) {
        if(!restaurant.acceptClient(this)) return;
        this.invitePeople();
        payRestaurant(restaurant);
    }

    protected void payRestaurant(Restaurant restaurant) {
        double amount = new DistributionAmountGenerator().generate(restaurant,this);
        Bank.makeTransaction(new EatingSaleTransaction(restaurant,this,amount));
    }


    @Override
    public void pay(double amount) {
        if(routineList != null) this.getRoutineList().decreaseBudget(amount);
    }

    @Override
    public void collect(double amount) {
        if(routineList != null) this.getRoutineList().increaseBudget(amount);
    }

    @Override
    public void collectSalary() {
        if(routineList != null) routineList.restartBudget();
    }

    @Override
    public String getMessage() {
        if(!Simulation.getClientListCopy().contains(this)) return this.getFullName() + " has died.";
        return "";
    }
}
