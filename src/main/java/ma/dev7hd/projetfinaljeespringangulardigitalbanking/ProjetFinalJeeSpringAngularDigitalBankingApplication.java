package ma.dev7hd.projetfinaljeespringangulardigitalbanking;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.BankAccountDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.CustomerDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.InsufficientBalanceException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.services.BankAccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            Stream.of("Hamza", "Mohammed", "Khadija", "Mustapha").forEach(name ->{
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
}
