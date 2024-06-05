package ma.dev7hd.projetfinaljeespringangulardigitalbanking.mappers;


import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AppMapper {
    public Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public CurrentBankAccountDTO toCurrentBankAccountDTO(CurrentAccount currentBankAccount) {
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentBankAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(toCustomerDTO(currentBankAccount.getCustomer()));
        return currentBankAccountDTO;
    }

    public CurrentAccount toCurrentBankAccount(CurrentBankAccountDTO currentBankAccountDTO) {
        CurrentAccount currentBankAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentBankAccount);
        currentBankAccount.setCustomer(toCustomer(currentBankAccountDTO.getCustomerDTO()));
        return currentBankAccount;
    }

    public SavingBankAccountDTO toSavingBankAccountDTO(SavingAccount savingBankAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingBankAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(toCustomerDTO(savingBankAccount.getCustomer()));
        return savingBankAccountDTO;
    }

    public SavingAccount toSavingBankAccount(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount savingBankAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingBankAccount);
        savingBankAccount.setCustomer(toCustomer(savingBankAccountDTO.getCustomerDTO()));
        return savingBankAccount;
    }

    public BankAccountDTO toBankAccountDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountDTO);
        bankAccountDTO.setCustomerDTO(toCustomerDTO(bankAccount.getCustomer()));
        return bankAccountDTO;
    }

    public BankAccount toBankAccount(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO, bankAccount);
        bankAccount.setCustomer(toCustomer(bankAccountDTO.getCustomerDTO()));
        return bankAccount;
    }

    public OperationDTO toOperationDTO(Operation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation,operationDTO);
        return operationDTO;
    }

    public Operation toOperation(OperationDTO operationDTO){
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDTO,operation);
        return operation;
    }
}
