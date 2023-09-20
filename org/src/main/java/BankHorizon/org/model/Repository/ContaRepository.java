package BankHorizon.org.model.Repository;





import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import BankHorizon.org.model.models.Conta;
import java.util.List;




@Repository
public interface ContaRepository extends JpaRepository <Conta,Long> {
    List<Conta> findByNumero(Long numero);
    
}