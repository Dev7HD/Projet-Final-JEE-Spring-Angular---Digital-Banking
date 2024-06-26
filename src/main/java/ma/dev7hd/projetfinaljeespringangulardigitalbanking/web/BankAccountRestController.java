package ma.dev7hd.projetfinaljeespringangulardigitalbanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.BankAccountNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.services.IBankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
@AllArgsConstructor
public class BankAccountRestController {
    private final IBankAccountService bankAccountService;

    @GetMapping("/account/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable(name = "id") String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("accounts/customer/{id}")
    public List<BankAccountDTO> getCustomerAccounts(@PathVariable Long id){
        return bankAccountService.getCustomerAccounts(id);
    }

    @GetMapping("/accounts/all")
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.listBankAccounts();
    }

    @PostMapping("/accounts/ca")
    public CurrentBankAccountDTO saveCurrentAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(initialBalance,customerId,overDraft);
    }

    @PostMapping("/accounts/sa")
    public SavingBankAccountDTO saveSavingAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(initialBalance,customerId,interestRate);
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

    @GetMapping("/operations")
    public List<OperationDTO> allOperations() {
        return bankAccountService.getOperations();
    }

    @GetMapping("accounts/count-all")
    public long[] getCountCurrentAccounts() {
        long countCurrentAccounts = bankAccountService.getCountCurrentAccounts();
        long countSavingAccounts = bankAccountService.getCountSavingAccounts();
        return new long[]{countCurrentAccounts,countSavingAccounts};
    }

    @GetMapping("operations/count")
    public List<Object[]> getOperationsCount(){
        return bankAccountService.getOperationsCountByMonth();
    }
}
