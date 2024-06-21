package ma.dev7hd.projetfinaljeespringangulardigitalbanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos.CustomerDTO;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions.CustomerNotFoundException;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.services.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountServiceImpl bankAccountService;

    @GetMapping("/customers/all")
    public List<CustomerDTO> getAllCustomers() {
        return bankAccountService.listCustomer();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(id);
    }

    @PostMapping("/customers/new")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        getCustomerById(id);
        customerDTO.setId(id);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        getCustomerById(id);
        bankAccountService.deleteCustomer(id);
    }
}
