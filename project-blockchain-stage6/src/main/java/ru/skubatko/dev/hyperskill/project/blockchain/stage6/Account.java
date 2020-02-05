package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

public class Account {
    private String name;
    private int balance;

    public Account(String name) {
        this.name = name;
        this.balance = 100;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
