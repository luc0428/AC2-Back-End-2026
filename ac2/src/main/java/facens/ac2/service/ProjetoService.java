package facens.ac2.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import facens.ac2.model.ProjetoModel;
import facens.ac2.repository.ProjetoRepository;
@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<ProjetoModel> ReadAll() {
        return projetoRepository.findAll();
    }

    public ProjetoModel ReadById(Long id) {
        return projetoRepository.findById(id).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
    }

    public ProjetoModel Create(ProjetoModel projeto){

        projeto.setId(null);
        return projetoRepository.save(projeto);
    }

    public ProjetoModel Update(Long id, ProjetoModel projetoAtualizado) {
     
        ProjetoModel projetoExistente = ReadById(id);
        projetoExistente.setDescricao(projetoAtualizado.getDescricao());
        projetoExistente.setDataInicio(projetoAtualizado.getDataInicio());
        projetoExistente.setDataFim(projetoAtualizado.getDataFim());

        return projetoRepository.save(projetoExistente);
    }

    public void Delete(Long id) {
        ProjetoModel projetoExistente = ReadById(id);
        projetoRepository.delete(projetoExistente);
    }
}
