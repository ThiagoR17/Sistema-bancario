package BankHorizon.org.Controller;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BankHorizon.org.Exepction.ContaNaoEncontradaException;
import BankHorizon.org.Exepction.SaldoInsuficienteException;
import BankHorizon.org.Service.ContasIguaisException;
import BankHorizon.org.Service.TransferenciaService;
import BankHorizon.org.model.models.Transferencia;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PutMapping("/transação")
    public ResponseEntity<?> transferir(@RequestBody Map<String, Object> requestBody) {
        try {
            Long contaOrigemId = ((Number) requestBody.get("contaOrigem")).longValue();
            Long contaDestinoId = ((Number) requestBody.get("contaDestino")).longValue();
            double valor = ((Number) requestBody.get("valor")).doubleValue();

            Transferencia transferencia = transferenciaService.realizarTransferencia(contaOrigemId, contaDestinoId, BigDecimal.valueOf(valor));
            return ResponseEntity.ok(transferencia);
        } catch (ContaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta de origem ou destino não encontrada.");
        } catch (SaldoInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente para realizar a transferência.");
        } catch (ContasIguaisException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As contas de origem e destino não podem ser iguais.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }
}
