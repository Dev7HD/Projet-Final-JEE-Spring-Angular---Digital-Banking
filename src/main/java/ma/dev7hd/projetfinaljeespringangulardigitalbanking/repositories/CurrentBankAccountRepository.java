package ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentBankAccountRepository extends JpaRepository<CurrentAccount, String> {
}
