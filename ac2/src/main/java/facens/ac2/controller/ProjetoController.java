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

import facens.ac2.model.ProjetoModel;
import facens.ac2.service.ProjetoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Projeto")
public class ProjetoController {

    private ProjetoService projetoService;

    public ProjetoController(ProjetoService ProjetoService){
        this.projetoService = ProjetoService;
    }

    @GetMapping("/Read")
    public List<ProjetoModel> listarProjetos(){
        return projetoService.ReadAll();
    }

    @GetMapping("/Projeto/teste")
    public String testeAPI(){
        return " API funciona";
    }

    @GetMapping("/ReadId/{id}")
    public ProjetoModel ReadById(@PathVariable Long id){
        return projetoService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoModel created(@RequestBody ProjetoModel projeto){
        return projetoService.Create(projeto);
    }

    @PutMapping("/UpdateProject/{id}")
    public ProjetoModel update (@PathVariable Long id, @RequestBody ProjetoModel projetoAtualizado){
        return projetoService.Update(id, projetoAtualizado);
    }

    @DeleteMapping("/DeleteProjeto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProjeto(@PathVariable Long id){
         projetoService.Delete(id);
    }

}
