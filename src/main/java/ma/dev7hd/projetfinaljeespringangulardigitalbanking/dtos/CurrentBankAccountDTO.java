package ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos;

import lombok.Data;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private double overDraft;
}
