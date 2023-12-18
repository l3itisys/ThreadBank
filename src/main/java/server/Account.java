package server;

public class Account {
    private int balance;
    private final int id;

    public Account(int balance, int id) {
        this.balance = balance;
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }
}

