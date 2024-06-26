package ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.entities.BankAccount;

import java.util.List;

@Getter @Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
