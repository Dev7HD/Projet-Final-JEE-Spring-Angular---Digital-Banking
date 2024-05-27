package ma.dev7hd.projetfinaljeespringangulardigitalbanking;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.AccountStatus;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.OperationType;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.BankAccountRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.CustomerRepository;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjetFinalJeeSpringAngularDigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFinalJeeSpringAngularDigitalBankingApplication.class, args);
    }

    @Bean
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
                    operation.setDate(new Date());
                    operation.setAmount(Math.random() * 10000 + 1000);
                    operationRepository.save(operation);
                }
            });
        };
    }

}
