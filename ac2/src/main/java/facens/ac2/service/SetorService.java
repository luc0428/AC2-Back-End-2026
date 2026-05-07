package facens.ac2.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import facens.ac2.model.SetorModel;
import facens.ac2.repository.SetorRepository;

@Service
public class SetorService {

    private final SetorRepository setorRepository;      

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<SetorModel> ReadAll() {
        return setorRepository.findAll();
    }   

    public SetorModel ReadById(Long id) {
        return setorRepository.findById(id).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));
    }

    public SetorModel Create(SetorModel setor){
        setor.setId(null);
        return setorRepository.save(setor);
    }

    public SetorModel Update(Long id, SetorModel setorAtualizado) {
        SetorModel setorExistente = ReadById(id);
        setorExistente.setNome(setorAtualizado.getNome());

        return setorRepository.save(setorExistente);
    }

    public void Delete(Long id) {
        SetorModel setorExistente = ReadById(id);
        setorRepository.delete(setorExistente);
    }

}
