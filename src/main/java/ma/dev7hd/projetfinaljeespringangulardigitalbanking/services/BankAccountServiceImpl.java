package ma.dev7hd.projetfinaljeespringangulardigitalbanking.services;

import lombok.AllArgsConstructor;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.BankAccount;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.CurrentAccount;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.Customer;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.SavingAccount;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.BankAccountType;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.BankAccountRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.CustomerRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public BankAccount saveBankAccount(double initialBalance, BankAccountType accountType, Long customerId) {
        BankAccount account;
        if(accountType == BankAccountType.SAVING_ACCOUNT){
            account = new SavingAccount();
        } else if(accountType == BankAccountType.CURRENT_ACCOUNT){
            account = new CurrentAccount();
        }

        return null;
              //  bankAccountRepository.save(account);
    }

    @Override
    public List<Customer> listCustomer() {
        return List.of();
    }

    @Override
    public BankAccount getBankaccount(String accountId) {
        return null;
    }

    @Override
    public void credit(double amount, String accountId, String description) {

    }

    @Override
    public void debit(double amount, String accountId, String description) {

    }

    @Override
    public void transfer(String accountSourceId, String accountDestinationId, double amount) {

    }
}
