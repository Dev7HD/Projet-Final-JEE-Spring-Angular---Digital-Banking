package ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingBankAccountRepository extends JpaRepository<SavingAccount, String> {
}
