package backend.model.client;

import backend.model.client.routine.RoutineList;
import backend.utils.BillsUtils;

import java.util.Date;

public class Client {
    PersonalData personalData;

    private RoutineList routineList;
    private int commensalNumber;


    public Client(String[] data) {
        commensalNumber = 0;
        this.personalData = new PersonalData(data);
    }

    public void pay(double amount){
        this.getRoutineList().decreaseBudget(amount);
    }

    public RoutineList getRoutineList() {
        return routineList;
    }

    public void setRoutineList(RoutineList routineList) {
        this.routineList = routineList;
    }

    public int getPeopleInvited() {
        commensalNumber = BillsUtils.getNumberPeopleSample();
        return commensalNumber;
    }


    public double getSalary(){
        return routineList.getSalary();
    }

    public void setSalary(double salary) {
        routineList.setSalary(salary);
    }

    public int getCommensalNumber() {
        return commensalNumber;
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

    public String getJob() {
        return personalData.getJob();
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

    public Date getBirthDate() {
        return personalData.getBirthDate();
    }
}
