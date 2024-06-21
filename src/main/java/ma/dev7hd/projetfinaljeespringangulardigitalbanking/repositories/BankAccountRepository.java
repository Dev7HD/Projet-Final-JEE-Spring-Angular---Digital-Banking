package ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.BankAccount;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(Long id);
    /*int countAllSavingsAccounts();
    int countAllCurrentAccounts();*/
}
