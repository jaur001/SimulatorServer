package backend.model.simulables.person.client;

import backend.implementations.routine.DistributionAmountGenerator;
import backend.model.simulables.Simulable;
import backend.model.simulables.bank.Bank;
import backend.model.simulables.bank.Collector;
import backend.model.simulables.bank.transactions.EatingBillTransaction;
import backend.model.simulables.company.complexCompany.complexCompanyWithStaff.restaurant.Restaurant;
import backend.model.simulables.person.client.routineList.RoutineList;
import backend.model.simulation.administration.centralControl.SimulationAdministrator;
import backend.model.simulation.settings.settingsList.ClientSettings;
import backend.utils.EuroFormatter;


public class Client implements Simulable, Collector{
    protected PersonalData personalData;
    protected RoutineList routineList;
    protected int peopleInvited;

    public Client(PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    @Override
    public int getNIF() {
        return personalData.getNIF();
    }

    public String getFirstName() {
        return personalData.getFirstName();
    }

    public String getLastName() {
        return personalData.getLastName();
    }

    @Override
    public String getName(){
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

    public double getSalary(){
        return personalData.getSalary();
    }

    public void setSalary(double salary) {
        if(routineList!=null)routineList.setSalary(salary);
        personalData.setSalary(salary);
    }

    public double getSalarySpent(){
        return routineList != null? routineList.getSalarySpent() : 0;
    }


    @Override
    public void simulate() {
        doClientsThings();
    }

    protected void doClientsThings() {
        if(ClientSettings.isGoingToDie(this.getAge())) {
            SimulationAdministrator.isGoingToDie(this);
            return;
        }
        routineList.checkRoutines().forEach(this::goToEat);
    }

    protected void goToEat(Restaurant restaurant) {
        if(!restaurant.acceptClient(this)) return;
        this.invitePeople();
        payRestaurant(restaurant);
    }

    protected void payRestaurant(Restaurant restaurant) {
        double amount = new DistributionAmountGenerator().generate(restaurant,this);
        Bank.makeTransaction(new EatingBillTransaction(restaurant,this,amount));
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

    public String getSalaryToShow() {
        return this.getSalary()==0.0?"Unemployed": EuroFormatter.format(this.getSalary());
    }

    public String getCurrencySalary() {
        return this.getSalary()==0.0?"Unemployed": EuroFormatter.formatEuro(this.getSalary());
    }
}
