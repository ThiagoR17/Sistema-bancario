package BankHorizon.org.model.Repository;

import org.springframework.stereotype.Repository;

import BankHorizon.org.model.models.Transferencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    Optional<Transferencia> findById(Long id);
}
