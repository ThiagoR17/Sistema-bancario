package BankHorizon.org.Exepction;

public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
    
}
