package model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class BankAccount {

    private String _id;
    private String nameOfBank;
    private String number;
    private long balance;
    private String userId;

    public BankAccount(String nameOfBank, String number, long balance, String userId) {

        this._id = String.valueOf(UUID.randomUUID());
        this.nameOfBank = nameOfBank;
        this.number = number;
        this.balance = balance;
        this.userId = userId;
    }

    public BankAccount() {

        this._id = String.valueOf(UUID.randomUUID());
    }
}
