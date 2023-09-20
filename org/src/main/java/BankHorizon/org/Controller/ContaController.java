package BankHorizon.org.Controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import BankHorizon.org.Exepction.ContaExistenteException;
import BankHorizon.org.Exepction.ContaNaoEncontradaException;

import BankHorizon.org.Service.ContaService;
import BankHorizon.org.model.models.Conta;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    
    @PostMapping("/criar")
    public ResponseEntity<?> criarConta(@RequestBody Conta conta) {
        try {
            Conta novaConta = contaService.criarConta(conta);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
        } catch (ContaExistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A pessoa já possui uma conta do mesmo tipo.");
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @GetMapping("/saldo/{numero}")
    public ResponseEntity<?> consultarSaldo(@PathVariable Long numero) {
        try {
            BigDecimal saldo = contaService.consultarSaldo(numero);
            return ResponseEntity.ok(saldo);
        } catch (ContaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }
    
    
        @PutMapping("/depositar")
        public ResponseEntity<Conta> depositar(@RequestBody Map<String, Object> requestBody){
        Long id = ((Number) requestBody.get("id")).longValue();
        double valor = ((Number) requestBody.get("valor")).doubleValue();
        Conta result = contaService.depositar(id, valor);

        if(result!= null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}

        
    

        

