package BankHorizon.org.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BankHorizon.org.Exepction.ContaExistenteException;

import BankHorizon.org.Exepction.SaldoInsuficienteException;
import BankHorizon.org.model.Repository.ContaRepository;
import BankHorizon.org.model.models.Conta;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta transferir(Long id, BigDecimal valor) {
        Optional<Conta> poupancaOptional = contaRepository.findById(id);

        return poupancaOptional.map(contaPoupanca -> {
            BigDecimal saldo = contaPoupanca.getSaldo();

            if (saldo.compareTo(valor) >= 0) {
                contaPoupanca.transferir(valor);
                return contaRepository.save(contaPoupanca);
            } else {
                throw new SaldoInsuficienteException("Saldo insuficiente na conta.");
            }
        }).orElse(null);
    }




    public Conta depositar(Long id, double valor) {
        Optional<Conta> optionalContaPoupancaDepo = contaRepository.findById(id);

        return optionalContaPoupancaDepo.map(contaPoupanca -> {
            contaPoupanca.depositar(BigDecimal.valueOf(valor));
            return contaRepository.save(contaPoupanca);
        }).orElse(null);
    }

    public BigDecimal consultarSaldo(Long numero) {
        Optional<Conta> contaOptional = contaRepository.findById(numero);
        return contaOptional.map(Conta::getSaldo).orElse(null);
    }
    public Conta criarConta(Conta conta) {
        List<Conta> contasExistente = contaRepository.findByNumero(conta.getNumero());
        if (!contasExistente.isEmpty()) {
            throw new ContaExistenteException("Uma conta com o mesmo número já existe.");
        }
    
        
        conta.setSaldo(BigDecimal.ZERO);
    
       
        return contaRepository.save(conta);
    }
    
}
