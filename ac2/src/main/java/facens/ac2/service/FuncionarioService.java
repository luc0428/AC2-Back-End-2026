package facens.ac2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import facens.ac2.model.FuncionarioModel;
import facens.ac2.repository.FuncionarioRepository;
@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioModel> ReadAll() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel ReadById(long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
    }

    public FuncionarioModel Create( FuncionarioModel funcionario){
        funcionario.setid(null);
        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioModel Update(Long id, FuncionarioModel funcionarioAtualizado) {
        FuncionarioModel funcionarioExistente = ReadById(id);
        funcionarioExistente.setNome(funcionarioAtualizado.getNome());

        return funcionarioRepository.save(funcionarioExistente);
    }

    public void Delete(Long id) {
        FuncionarioModel funcionarioExistente = ReadById(id);
        funcionarioRepository.delete(funcionarioExistente);
    }
}
