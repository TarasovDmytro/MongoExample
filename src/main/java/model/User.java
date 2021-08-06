package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@ToString
@Setter
@Getter
public final class User {

    private String _id;
    private String firstName;
    private String lastName;
    private int age;
    private String company;
    private String city;
    private List<BankAccount> bankAccount;

    public User(String firstName, String lastName, int age, String company, String city, List<BankAccount> bankAccount) {

        this._id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.company = company;
        this.city = city;
        this.bankAccount = bankAccount;
    }

    public User() {

        this._id = String.valueOf(UUID.randomUUID());
    }
}
