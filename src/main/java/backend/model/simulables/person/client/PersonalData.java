package backend.model.simulables.person.client;

import backend.model.NIFCreator.PersonNIFCreator;
import backend.model.simulation.timeLine.TimeLine;

public class PersonalData {
    private int NIF;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String birthDate;
    private String job;
    private double salary;
    private String country;
    private String telephoneNumber;
    private String cardNumber;


    public PersonalData(int NIF,String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        this.NIF = NIF;
        init(firstName, lastName, birthDate, gender, job, country, telephoneNumber, email, cardNumber);
    }

    public PersonalData(String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        this.NIF = new PersonNIFCreator().create();
        init(firstName, lastName, birthDate, gender, job, country, telephoneNumber, email, cardNumber);
    }

    private void init(String firstName, String lastName, String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.job = job;
        this.salary = 0;
        this.country = country;
        this.telephoneNumber = telephoneNumber;
        this.cardNumber = cardNumber;
    }

    public int getNIF() {
        return NIF;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge(){
        return TimeLine.getYear()-Integer.parseInt(birthDate.substring(getYear()+1));
    }

    public int getYear() {
        return birthDate.indexOf("/",birthDate.length()-6);
    }
}
