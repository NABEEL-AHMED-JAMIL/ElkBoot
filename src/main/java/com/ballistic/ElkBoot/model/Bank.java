package com.ballistic.ElkBoot.model;

import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bank", type = "backAccount")
public class Bank {

    @Id
    private String accountNumber;
    private Integer balance;
    private String firstName;
    private String lastName;
    private Integer age;
    private Character gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;

    public Bank() { }

    public Bank(Integer balance, String firstName, String lastName, Integer age,
                Character gender, String address, String employer, String email, String city, String state) {
        this.balance = balance;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.employer = employer;
        this.email = email;
        this.city = city;
        this.state = state;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Character getGender() { return gender; }
    public void setGender(Character gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmployer() { return employer; }
    public void setEmployer(String employer) { this.employer = employer; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public String toString() { return new Gson().toJson(this); }

}
