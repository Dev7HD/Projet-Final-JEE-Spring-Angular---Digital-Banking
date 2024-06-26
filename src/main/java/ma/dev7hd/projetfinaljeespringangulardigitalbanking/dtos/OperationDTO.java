package ma.dev7hd.projetfinaljeespringangulardigitalbanking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dev7hd.projetfinaljeespringangulardigitalbanking.enumirats.OperationType;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperationDTO {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
    private String description;
}
