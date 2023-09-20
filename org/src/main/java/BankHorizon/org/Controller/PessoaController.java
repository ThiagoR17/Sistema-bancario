package BankHorizon.org.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BankHorizon.org.Exepction.PessoaExistenteException;
import BankHorizon.org.Service.PessoaService;
import BankHorizon.org.model.models.Pessoa;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

  @PostMapping("/login")
  public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa){
    try{
        Pessoa novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }catch (PessoaExistenteException e ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A pessoa já existe");
    }catch(ValidationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação" + e.getMessage());
    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
    }
  }
}
