package ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccount_Id(String accountId);
    Page<Operation> findByAccount_Id(String accountId, Pageable pageable);
    @Query("SELECT MONTH(o.date) AS month, COUNT(o) AS count FROM Operation o WHERE o.type = 'DEBIT' GROUP BY MONTH(o.date)")
    List<Object[]> countDebitOperationsByMonth();
    @Query("SELECT MONTH(o.date) AS month, COUNT(o) AS count FROM Operation o WHERE o.type = 'CREDIT' GROUP BY MONTH(o.date)")
    List<Object[]> countCreditOperationsByMonth();
}
