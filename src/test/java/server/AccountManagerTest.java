package server;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountManagerTest {

    private AccountManager accountManager;

    @Before
    public void setUp() {
        accountManager = new AccountManager();
        // The accounts are initialized in the AccountManager constructor
    }

    @Test
    public void transferFundsSuccessfully() throws Exception {
        accountManager.transferFunds(123, 456, 1000);
        assertEquals(9000, accountManager.getBalance(123));
        assertEquals(6000, accountManager.getBalance(456));
    }

    @Test
    public void transferFundsInsufficientBalance() {
        try {
            accountManager.transferFunds(123, 456, 15000); // Attempt to transfer more than the account's balance
            fail("Expected an Exception to be thrown due to insufficient funds");
        } catch (Exception e) {
            assertEquals("Insufficient funds in account 123", e.getMessage());
        }
    }

    // Additional test methods can be added here
}

