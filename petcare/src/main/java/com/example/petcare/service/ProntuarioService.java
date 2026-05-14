package com.example.petcare.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.petcare.model.AnimalModel;
import com.example.petcare.model.ConsultaModel;
import com.example.petcare.model.ProntuarioModel;
import com.example.petcare.repository.AnimalRepository;
import com.example.petcare.repository.ConsultaRepository;
import com.example.petcare.repository.ProntuarioRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AnimalRepository animalRepository;
    private final ConsultaRepository consultaRepository;

    public ProntuarioService(
            ProntuarioRepository prontuarioRepository,
            AnimalRepository animalRepository,
            ConsultaRepository consultaRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.animalRepository = animalRepository;
        this.consultaRepository = consultaRepository;
    }

    public List<ProntuarioModel> ReadAll() {
        return prontuarioRepository.findAll();
    }

    public ProntuarioModel ReadById(Long id) {
        return prontuarioRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuario nao encontrado"));
    }

    public ProntuarioModel Create(ProntuarioModel prontuario) {
        prontuario.setId(null);
        ProntuarioModel validado = validarProntuario(prontuario);
        return prontuarioRepository.save(validado);
    }

    public ProntuarioModel Update(Long id, ProntuarioModel prontuarioAtualizado) {
        ProntuarioModel prontuarioExistente = ReadById(id);
        ProntuarioModel validado = validarProntuario(prontuarioAtualizado);

        prontuarioExistente.setDataRegistro(validado.getDataRegistro());
        prontuarioExistente.setDescricao(validado.getDescricao());
        prontuarioExistente.setAnimal(validado.getAnimal());
        prontuarioExistente.setConsulta(validado.getConsulta());

        return prontuarioRepository.save(prontuarioExistente);
    }

    public void Delete(Long id) {
        ProntuarioModel prontuarioExistente = ReadById(id);
        prontuarioRepository.delete(prontuarioExistente);
    }

    private ProntuarioModel validarProntuario(ProntuarioModel prontuario) {
        if (prontuario.getDescricao() == null || prontuario.getDescricao().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descricao obrigatoria");
        }

        AnimalModel animal = validarAnimal(prontuario);
        ConsultaModel consulta = validarConsulta(prontuario);

        if (prontuario.getDataRegistro() == null) {
            prontuario.setDataRegistro(LocalDate.now());
        }

        prontuario.setDescricao(prontuario.getDescricao().trim());
        prontuario.setAnimal(animal);
        prontuario.setConsulta(consulta);

        return prontuario;
    }

    private AnimalModel validarAnimal(ProntuarioModel prontuario) {
        if (prontuario.getAnimal() == null || prontuario.getAnimal().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal obrigatorio");
        }

        return animalRepository.findById(prontuario.getAnimal().getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal nao encontrado"));
    }

    private ConsultaModel validarConsulta(ProntuarioModel prontuario) {
        if (prontuario.getConsulta() == null || prontuario.getConsulta().getId() == null) {
            return null;
        }

        return consultaRepository.findById(prontuario.getConsulta().getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta nao encontrada"));
    }
}
