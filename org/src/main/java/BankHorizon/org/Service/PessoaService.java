package BankHorizon.org.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BankHorizon.org.Exepction.PessoaExistenteException;
import BankHorizon.org.model.Repository.PessoaRepository;
import BankHorizon.org.model.models.Pessoa;
@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }
    public Pessoa criarPessoa(Pessoa pessoa) {
        if (pessoaRepository.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new PessoaExistenteException("A pessoa já existe.");
        }

        
        return pessoaRepository.save(pessoa);
    }

}   
