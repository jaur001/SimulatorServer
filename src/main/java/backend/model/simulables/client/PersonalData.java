package backend.model.simulables.client;

import backend.model.NIFCreator.PersonNIFCreator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

public class PersonalData {
    private int NIF;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String birthDate;
    private String job;
    private String country;
    private String telephoneNumber;
    private String cardNumber;

    public PersonalData(String[] data) {
        this.NIF = new PersonNIFCreator().create();
        init(data[1],data[2],data[3]
                ,data[4],data[5],data[6]
                ,data[7],data[8],data[9]);

    }

    public PersonalData(Object[] data) {
        this.NIF = (int)data[0];
        init((String) data[1],(String) data[2],(String)data[3]
                ,(String) data[4],(String) data[5],(String) data[6]
                ,(String) data[7],(String) data[8],(String) data[9]);
    }

    public PersonalData(int NIF,String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        this.NIF = NIF;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.job = job;
        this.country = country;
        this.telephoneNumber = telephoneNumber;
        this.cardNumber = cardNumber;
    }

    public void init(String firstName, String lastName,String birthDate, String gender, String job, String country, String telephoneNumber, String email, String cardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.job = job;
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

    public String getJob() {
        return job;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
