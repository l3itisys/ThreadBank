package server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountManager {
    private final Map<Integer, Account> accounts = new ConcurrentHashMap<>();

    public AccountManager() {
        accounts.put(123, new Account(10000, 123));
        accounts.put(456, new Account(5000, 456));
    }

    public void transferFunds(int fromAccountId, int toAccountId, int amount) throws Exception {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new Exception("One or more accounts not found.");
        }

        synchronized (fromAccount) {
            if (fromAccount.getBalance() < amount) {
                throw new Exception("Insufficient funds in account " + fromAccountId);
            }
            fromAccount.withdraw(amount);
        }

        synchronized (toAccount) {
            toAccount.deposit(amount);
        }
    }
}

