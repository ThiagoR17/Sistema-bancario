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
        // Aqui você pode adicionar validações ou lógica de negócios, se necessário
        // Por exemplo, verifique se a pessoa já existe antes de criar
        if (pessoaRepository.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new PessoaExistenteException("A pessoa já existe.");
        }

        // Salve a nova pessoa no banco de dados
        return pessoaRepository.save(pessoa);
    }

    // Outros métodos do serviço
}
