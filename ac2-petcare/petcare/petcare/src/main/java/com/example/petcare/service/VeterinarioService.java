package com.example.petcare.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.petcare.model.VeterinarioModel;
import com.example.petcare.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<VeterinarioModel> ReadAll() {
        return veterinarioRepository.findAll();
    }

    public VeterinarioModel ReadById(Long id) {
        return veterinarioRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinario nao encontrado"));
    }

    public VeterinarioModel Create(VeterinarioModel veterinario) {
        veterinario.setId(null);
        return veterinarioRepository.save(veterinario);
    }

    public VeterinarioModel Update(Long id, VeterinarioModel veterinarioAtualizado) {
        VeterinarioModel veterinarioExistente = ReadById(id);
        veterinarioExistente.setNome(veterinarioAtualizado.getNome());
        veterinarioExistente.setEspecialidade(veterinarioAtualizado.getEspecialidade());
        veterinarioExistente.setCrmv(veterinarioAtualizado.getCrmv());

        return veterinarioRepository.save(veterinarioExistente);
    }

    public void Delete(Long id) {
        VeterinarioModel veterinarioExistente = ReadById(id);
        veterinarioRepository.delete(veterinarioExistente);
    }
}
