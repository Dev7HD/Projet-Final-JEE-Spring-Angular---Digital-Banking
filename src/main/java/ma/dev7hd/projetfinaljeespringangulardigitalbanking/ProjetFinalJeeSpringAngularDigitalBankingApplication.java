package ma.dev7hd.projetfinaljeespringangulardigitalbanking;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.BankAccountDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.CustomerDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.AccountStatus;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.OperationType;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.InsufficientBalanceException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.BankAccountRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.CustomerRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.OperationRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.services.BankAccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjetFinalJeeSpringAngularDigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFinalJeeSpringAngularDigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountServiceImpl bankAccountService){
        return args -> {
            Stream.of("Hamza", "Hind", "Khadija", "Mustapha").forEach(name ->{
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(name);
                customerDTO.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customerDTO);
            });
            bankAccountService.listCustomer().forEach(customer -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        double random = Math.random();
                        if (random < 0.5) {
                            bankAccountService.saveCurrentBankAccount(Math.random()*900000,customer.getId(),5000);
                        } else {
                            bankAccountService.saveSavingBankAccount(Math.random()*900000,customer.getId(),3.5);
                        }
                    }
                    List<BankAccountDTO> bankAccounts = bankAccountService.listBankAccounts();
                    for (BankAccountDTO bankAccount : bankAccounts) {
                        for (int i = 0; i < 10; i++) {
                            bankAccountService.credit(Math.random()*15000,bankAccount.getId(),"Credit");
                        }
                        for (int i = 0; i < 6; i++) {
                            bankAccountService.debit(1000 + Math.random()*5000,bankAccount.getId(),"Debit");
                        }
                    }
                } catch (CustomerNotFoundException | BankAccountNotFoundException | InsufficientBalanceException e) {
                    e.printStackTrace();
                }
            });
        };
    }
    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            OperationRepository operationRepository) {
        return args -> {
            Stream.of("Hamza", "Hind", "Khadija", "Mustapha").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setBalance(Math.random() * 20000 + 5000);
                currentAccount.setCurrency("MAD");
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(5000);
                bankAccountRepository.save(currentAccount);
            });

            customerRepository.findAll().forEach(customer -> {
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setBalance(Math.random() * 20000 + 5000);
                savingAccount.setCurrency("MAD");
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(4.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 5; i++) {
                    Operation operation = new Operation();
                    operation.setAccount(account);
                    operation.setType(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT);
                    Date date = new Date(2024, 6,15);
                    operation.setDate(date);
                    System.out.println(date);
                    operation.setAmount(Math.random() * 10000 + 1000);
                    operationRepository.save(operation);
                }
            });
        };
    }

}
