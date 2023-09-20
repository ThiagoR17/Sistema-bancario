package BankHorizon.org.Exepction;

public class SaldoInsuficienteException extends RuntimeException{
    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }
    
}
