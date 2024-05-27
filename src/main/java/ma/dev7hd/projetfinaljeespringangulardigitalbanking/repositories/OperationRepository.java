package ma.dev7hd.projetfinaljeespringangulardigitalbanking.repositories;

import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
