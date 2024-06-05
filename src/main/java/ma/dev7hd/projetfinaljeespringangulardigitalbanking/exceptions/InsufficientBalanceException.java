package ma.dev7hd.projetfinaljeespringangulardigitalbanking.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
