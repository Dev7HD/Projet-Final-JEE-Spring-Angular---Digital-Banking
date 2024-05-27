package ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.AccountStatus;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
@Data @AllArgsConstructor @NoArgsConstructor
public class BankAccount {
    @Id @UuidGenerator
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "account")
    private List<Operation> operations;
}
