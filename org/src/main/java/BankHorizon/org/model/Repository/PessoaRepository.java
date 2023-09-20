package BankHorizon.org.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BankHorizon.org.model.models.Pessoa;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findById(Long id);

    Optional<Pessoa> findByCpf(String cpf);
}
