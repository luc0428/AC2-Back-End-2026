package com.example.petcare.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.petcare.model.AnimalModel;
import com.example.petcare.model.VacinacaoModel;
import com.example.petcare.repository.AnimalRepository;
import com.example.petcare.repository.VacinacaoRepository;

@Service
public class VacinacaoService {

    private final VacinacaoRepository vacinacaoRepository;
    private final AnimalRepository animalRepository;

    public VacinacaoService(VacinacaoRepository vacinacaoRepository, AnimalRepository animalRepository) {
        this.vacinacaoRepository = vacinacaoRepository;
        this.animalRepository = animalRepository;
    }

    public List<VacinacaoModel> ReadAll() {
        return vacinacaoRepository.findAll();
    }

    public VacinacaoModel ReadById(Long id) {
        return vacinacaoRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacinacao nao encontrada"));
    }

    public VacinacaoModel Create(VacinacaoModel vacinacao) {
        vacinacao.setId(null);
        VacinacaoModel validada = validarVacinacao(vacinacao);
        return vacinacaoRepository.save(validada);
    }

    public VacinacaoModel Update(Long id, VacinacaoModel vacinacaoAtualizada) {
        VacinacaoModel vacinacaoExistente = ReadById(id);
        VacinacaoModel validada = validarVacinacao(vacinacaoAtualizada);

        vacinacaoExistente.setVacina(validada.getVacina());
        vacinacaoExistente.setDataAplicacao(validada.getDataAplicacao());
        vacinacaoExistente.setProximaDose(validada.getProximaDose());
        vacinacaoExistente.setAnimal(validada.getAnimal());

        return vacinacaoRepository.save(vacinacaoExistente);
    }

    public void Delete(Long id) {
        VacinacaoModel vacinacaoExistente = ReadById(id);
        vacinacaoRepository.delete(vacinacaoExistente);
    }

    private VacinacaoModel validarVacinacao(VacinacaoModel vacinacao) {
        if (vacinacao.getVacina() == null || vacinacao.getVacina().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vacina obrigatoria");
        }

        AnimalModel animal = validarAnimal(vacinacao);
        if (vacinacao.getDataAplicacao() == null) {
            vacinacao.setDataAplicacao(LocalDate.now());
        }

        vacinacao.setVacina(vacinacao.getVacina().trim());
        vacinacao.setAnimal(animal);

        return vacinacao;
    }

    private AnimalModel validarAnimal(VacinacaoModel vacinacao) {
        if (vacinacao.getAnimal() == null || vacinacao.getAnimal().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal obrigatorio");
        }

        return animalRepository.findById(vacinacao.getAnimal().getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal nao encontrado"));
    }
}
