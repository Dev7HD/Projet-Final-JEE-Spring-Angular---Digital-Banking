package ma.dev7hd.projetfinaljeespringangulardigitalbanking.services;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.BankAccount;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.Customer;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.BankAccountType;

import java.util.List;

public interface IBankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveBankAccount(double initialBalance, BankAccountType accountType, Long customerId);
    List<Customer> listCustomer();
    BankAccount getBankaccount(String accountId);
    void credit(double amount, String accountId, String description);
    void debit(double amount, String accountId, String description);
    void transfer(String accountSourceId, String accountDestinationId, double amount);
}
