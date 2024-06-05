package ma.dev7hd.projetfinaljeespringangulardigitalbanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.BankAccountDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.CurrentBankAccountDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.SavingBankAccountDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.services.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class BankAccountRestController {
    private final BankAccountServiceImpl bankAccountServiceImpl;
    private BankAccountServiceImpl bankAccountService;

    @GetMapping("/account/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping("/accounts/all")
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.listBankAccounts();
    }

    @PostMapping("/accounts/ca")
    public CurrentBankAccountDTO saveCurrentAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException {
        return bankAccountServiceImpl.saveCurrentBankAccount(initialBalance,customerId,overDraft);
    }

    @PostMapping("/accounts/sa")
    public SavingBankAccountDTO saveSavingAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException {
        return bankAccountServiceImpl.saveSavingBankAccount(initialBalance,customerId,interestRate);
    }



}
