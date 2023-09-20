package BankHorizon.org.model.models;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Transferencia")
public class Transferencia {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contaorigem")
    private Long contaorigem;

    @Column(name = "contadestino")
    private Long contadestino;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "valor")
    private BigDecimal valor;


    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }

   
    public void setId(Long id) {
        this.id = id;
    }

  
    public void setContaorigem(Long contaorigem) {
        this.contaorigem = contaorigem;
    }

    public void setContadestino(Long contadestino) {
        this.contadestino = contadestino;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getContaorigem() {
        return  contaorigem;
    }

    public Long getContadestino() {
        return contadestino;
    }

    public void setValor(double doubleValue) {
    }

}
