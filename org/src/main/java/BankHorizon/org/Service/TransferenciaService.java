package BankHorizon.org.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BankHorizon.org.Exepction.ContaNaoEncontradaException;
import BankHorizon.org.Exepction.SaldoInsuficienteException;
import BankHorizon.org.model.Repository.ContaRepository;
import BankHorizon.org.model.models.Conta;
import BankHorizon.org.model.models.Transferencia;

@Service
public class TransferenciaService {
    private final ContaRepository contaRepository;

    @Autowired
    public TransferenciaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Transferencia realizarTransferencia(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        if (contaOrigemId.equals(contaDestinoId)) {
            throw new ContasIguaisException("As contas de origem e destino não podem ser iguais.");
        }

        Conta contaOrigem = contaRepository.findById(contaOrigemId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de origem não encontrada."));
        Conta contaDestino = contaRepository.findById(contaDestinoId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de destino não encontrada."));
        if (contaOrigem.getSaldo().compareTo(valor) >= 0) {
           
            contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
            contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

            contaRepository.save(contaOrigem);
            contaRepository.save(contaDestino);

            Transferencia transferenciaRealizada = new Transferencia();
            transferenciaRealizada.setContaorigem(contaOrigemId);
            transferenciaRealizada.setContadestino(contaDestinoId);
            transferenciaRealizada.setValor(valor);
            transferenciaRealizada.setData(LocalDate.now());

            return transferenciaRealizada;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente na conta de origem para realizar a transferência.");
        }
    }
}
