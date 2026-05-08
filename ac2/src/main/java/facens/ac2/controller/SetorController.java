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
import facens.ac2.model.ProjetoModel;
import facens.ac2.model.SetorModel;
import facens.ac2.service.ProjetoService;
import facens.ac2.service.SetorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Setor")
public class SetorController {
    
    private SetorService setorService;

    public SetorController(SetorService SetorService){
        this.setorService = SetorService;
    }

 @GetMapping("/Read")
    public List<SetorModel> listarSetores(){
        return setorService.ReadAll();
    }

 @GetMapping("/ReadId/{id}")
    public SetorModel ReadById(@PathVariable Long id){
        return setorService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetorModel created(@RequestBody SetorModel setor){
        return setorService.Create(setor);
    }

    @PutMapping("/UpdateProject/{id}")
    public SetorModel update (@PathVariable Long id, @RequestBody SetorModel setorAtualizado){
        return setorService.Update(id, setorAtualizado);
    }

    @DeleteMapping("/DeleteSetor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerSetor(@PathVariable Long id){
         setorService.Delete(id);
    }

}
