package BankHorizon.org.model.models;
import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Conta")
public class Conta implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoaid;
    
    @Column(name = "numero")
    private Long numero;

    @Column(name = "digito")
    private BigDecimal digito;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "tipo_conta")
    private String tipoConta;
    
    public Long getPessoaid() {
        return pessoaid;
    }

    public Long getNumero() {
        return numero;
    }

    public BigDecimal getDigito() {
        return digito;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    // Setters
    public void setPessoaid(Long pessoaid) {
        this.pessoaid = pessoaid;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setDigito(BigDecimal digito) {
        this.digito = digito;
    }


    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void transferir(BigDecimal saldo){
        this.saldo = saldo;
    }
    public void sacar(BigDecimal bigDecimal){
        this.saldo = bigDecimal;
    }
    public void depositar(BigDecimal bigDecimal){
        this.saldo = bigDecimal;
    }

    public Long getId() {
        return null;
    }

    public void setSaldo(BigDecimal zero) {
    }



}

  
