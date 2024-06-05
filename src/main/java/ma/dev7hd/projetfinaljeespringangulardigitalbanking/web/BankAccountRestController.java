package ma.dev7hd.projetfinaljeespringangulardigitalbanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.services.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class BankAccountRestController {
    private final BankAccountServiceImpl bankAccountServiceImpl;
    private BankAccountServiceImpl bankAccountService;

    @GetMapping("/account/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable(name = "id") String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
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

    @GetMapping("/accounts/{id}/operations")
    public List<OperationDTO> getBankAccountOperations(@PathVariable(name = "id") String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{id}/history")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable(name = "id") String accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }
}
