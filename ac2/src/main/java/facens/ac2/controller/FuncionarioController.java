package facens.ac2.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import facens.ac2.model.FuncionarioModel;
import facens.ac2.service.FuncionarioService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Funcionario")
public class FuncionarioController {


    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService FuncionarioService){
        this.funcionarioService = FuncionarioService;
    }

    //busca 
 @GetMapping("/Read")
    public List<FuncionarioModel> listarFuncionarios(){
        return funcionarioService.ReadAll();
    }

    //busca por id
 @GetMapping("/ReadId/{id}")
    public FuncionarioModel ReadById(@PathVariable Long id){
        return funcionarioService.ReadById(id);
    }

    //cadastro
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioModel created(@RequestBody FuncionarioModel funcionario){
        return funcionarioService.Create(funcionario);
    }

    //atualizar
    @PutMapping("/UpdateFuncionario/{id}")
    public FuncionarioModel update (@PathVariable Long id, @RequestBody FuncionarioModel funcionarioAtualizado){
        return funcionarioService.Update(id, funcionarioAtualizado);
    }

    //deletar
    @DeleteMapping("/DeleteFuncionario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFuncionario(@PathVariable Long id){
         funcionarioService.Delete(id);
    }

}
