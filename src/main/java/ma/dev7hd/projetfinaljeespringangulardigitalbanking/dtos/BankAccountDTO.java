package ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos;

import lombok.Getter;
import lombok.Setter;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.AccountStatus;

import java.util.Date;

@Getter
@Setter
public class BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private String currency;
    private CustomerDTO customerDTO;
}
