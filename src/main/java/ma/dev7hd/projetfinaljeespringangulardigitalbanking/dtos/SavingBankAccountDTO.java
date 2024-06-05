package ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingBankAccountDTO extends BankAccountDTO {
    private double interestRate;
}
