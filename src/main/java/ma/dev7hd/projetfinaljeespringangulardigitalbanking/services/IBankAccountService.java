package ma.dev7hd.projetfinaljeespringangulardigitalbanking.services;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.InsufficientBalanceException;

import java.util.List;

public interface IBankAccountService {
    CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException;
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomer();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    List<BankAccountDTO> listBankAccounts();

    void credit(double amount, String accountId, String description) throws BankAccountNotFoundException, InsufficientBalanceException;
    void debit(double amount, String accountId, String description) throws BankAccountNotFoundException, InsufficientBalanceException;
    void transfer(String accountSourceId, String accountDestinationId, double amount) throws BankAccountNotFoundException, InsufficientBalanceException;

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<OperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
