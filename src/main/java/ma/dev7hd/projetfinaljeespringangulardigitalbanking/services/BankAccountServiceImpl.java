package ma.dev7hd.projetfinaljeespringangulardigitalbanking.services;

import lombok.AllArgsConstructor;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.AccountStatus;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.OperationType;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.InsufficientBalanceException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.mappers.AppMapper;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.BankAccountRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.CustomerRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;
    private AppMapper appMapper;

    //Customer methods

    @Override
    public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return appMapper.toCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> appMapper.toCustomerDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return appMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = appMapper.toCustomer(customerDTO);
        return appMapper.toCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = appMapper.toCustomer(customerDTO);
        return appMapper.toCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    //BankAccount methods

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        CurrentAccount account = new CurrentAccount();
        account.setBalance(initialBalance);
        account.setCustomer(customer);
        account.setCreatedAt(new Date());
        account.setStatus(AccountStatus.CREATED);
        account.setCurrency("MAD");
        account.setOverDraft(overDraft);
        return appMapper.toCurrentBankAccountDTO(bankAccountRepository.save(account));
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        SavingAccount account = new SavingAccount();
        account.setBalance(initialBalance);
        account.setCustomer(customer);
        account.setCreatedAt(new Date());
        account.setStatus(AccountStatus.CREATED);
        account.setCurrency("MAD");
        account.setInterestRate(interestRate);
        return appMapper.toSavingBankAccountDTO(bankAccountRepository.save(account));
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException{
       BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
       if(bankAccount instanceof CurrentAccount currentAccount){
           return appMapper.toCurrentBankAccountDTO(currentAccount);
       } else {
           SavingAccount savingAccount = (SavingAccount) bankAccount;
           return appMapper.toSavingBankAccountDTO(savingAccount);
       }
    }

    @Override
    public List<BankAccountDTO> listBankAccounts(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream().map(account -> {
            if (account instanceof CurrentAccount currentAccount) {
                return appMapper.toCurrentBankAccountDTO(currentAccount);
            } else {
                SavingAccount savingAccount = (SavingAccount) account;
                return appMapper.toSavingBankAccountDTO(savingAccount);
            }
        }).toList();

    }

    @Override
    public void credit(double amount, String accountId, String description) throws BankAccountNotFoundException, InsufficientBalanceException {
        BankAccount account = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("The account balance is insufficient.");
        }
        Operation operation = new Operation();
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setAccount(account);
        operation.setDate(new Date());
        operation.setType(OperationType.CREDIT);
        operationRepository.save(operation);
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);
    }

    @Override
    public void debit(double amount, String accountId, String description) throws BankAccountNotFoundException, InsufficientBalanceException {
        BankAccount account = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        Operation operation = new Operation();
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setAccount(account);
        operation.setDate(new Date());
        operation.setType(OperationType.DEBIT);
        operationRepository.save(operation);
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
    }

    @Override
    public void transfer(String accountSourceId, String accountDestinationId, double amount) throws BankAccountNotFoundException, InsufficientBalanceException {
        debit(amount, accountSourceId, "Transfer to " + accountDestinationId);
        credit(amount, accountDestinationId, "Transfer from " + accountSourceId);
    }


}
