package backend.model.client;

import backend.utils.MathUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonalData {
    private static final AtomicInteger count = new AtomicInteger(MathUtils.random(10000000,99999999));
    private int NIF;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Date birthDate;
    private String job;
    private String country;
    private String telephoneNumber;
    private String cardNumber;

    public PersonalData(String firstName) {
        this.firstName = firstName;
        this.NIF = count.getAndIncrement();
        this.lastName = "xd";
        this.birthDate = new Date();
        this.gender = "xd";
        this.job = "xd";
        this.country = "xd";
        this.telephoneNumber = "xd";
        this.email = "xd";
        this.cardNumber = "xd";
    }

    public PersonalData(String[] data) {
        this.NIF = count.getAndIncrement();
        this.firstName = data[1];
        this.lastName = data[2];
        this.birthDate = new Date(data[3]);
        this.gender = data[4];
        this.job = data[5];
        this.country = data[6];
        this.telephoneNumber = data[7];
        this.email = data[8];
        this.cardNumber = data[9];
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

    public Date getBirthDate() {
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
