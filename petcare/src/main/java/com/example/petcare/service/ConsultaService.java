package com.example.petcare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.petcare.model.AnimalModel;
import com.example.petcare.model.ConsultaModel;
import com.example.petcare.model.VeterinarioModel;
import com.example.petcare.repository.AnimalRepository;
import com.example.petcare.repository.ConsultaRepository;
import com.example.petcare.repository.VeterinarioRepository;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final AnimalRepository animalRepository;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            VeterinarioRepository veterinarioRepository,
            AnimalRepository animalRepository) {
        this.consultaRepository = consultaRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.animalRepository = animalRepository;
    }

    public List<ConsultaModel> ReadAll() {
        return consultaRepository.findAll();
    }

    public ConsultaModel ReadById(Long id) {
        return consultaRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta nao encontrada"));
    }

    public ConsultaModel Create(ConsultaModel consulta) {
        consulta.setId(null);
        ConsultaModel validada = validarConsulta(consulta, null);
        return consultaRepository.save(validada);
    }

    public ConsultaModel Update(Long id, ConsultaModel consultaAtualizada) {
        ConsultaModel consultaExistente = ReadById(id);
        ConsultaModel validada = validarConsulta(consultaAtualizada, id);

        consultaExistente.setDataHora(validada.getDataHora());
        consultaExistente.setMotivo(validada.getMotivo());
        consultaExistente.setEspecialidadeRequerida(validada.getEspecialidadeRequerida());
        consultaExistente.setVeterinario(validada.getVeterinario());
        consultaExistente.setAnimal(validada.getAnimal());

        return consultaRepository.save(consultaExistente);
    }

    public void Delete(Long id) {
        ConsultaModel consultaExistente = ReadById(id);
        consultaRepository.delete(consultaExistente);
    }

    private ConsultaModel validarConsulta(ConsultaModel consulta, Long consultaId) {
        if (consulta.getDataHora() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data e hora obrigatorias");
        }

        VeterinarioModel veterinario = validarVeterinario(consulta);
        AnimalModel animal = validarAnimal(consulta);

        String especialidade = consulta.getEspecialidadeRequerida();
        if (especialidade == null || especialidade.trim().isEmpty()) {
            especialidade = veterinario.getEspecialidade();
        }

        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Especialidade obrigatoria");
        }

        if (!especialidade.trim().equalsIgnoreCase(veterinario.getEspecialidade())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Veterinario atende apenas sua especialidade");
        }

        validarConflitoAgenda(veterinario.getId(), consulta.getDataHora(), consultaId);

        consulta.setVeterinario(veterinario);
        consulta.setAnimal(animal);
        consulta.setEspecialidadeRequerida(especialidade.trim());

        return consulta;
    }

    private VeterinarioModel validarVeterinario(ConsultaModel consulta) {
        if (consulta.getVeterinario() == null || consulta.getVeterinario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veterinario obrigatorio");
        }

        return veterinarioRepository.findById(consulta.getVeterinario().getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinario nao encontrado"));
    }

    private AnimalModel validarAnimal(ConsultaModel consulta) {
        if (consulta.getAnimal() == null || consulta.getAnimal().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Animal obrigatorio");
        }

        return animalRepository.findById(consulta.getAnimal().getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal nao encontrado"));
    }

    private void validarConflitoAgenda(Long veterinarioId, LocalDateTime dataHora, Long consultaId) {
        boolean conflito;
        if (consultaId == null) {
            conflito = consultaRepository.existsByVeterinarioIdAndDataHora(veterinarioId, dataHora);
        } else {
            conflito = consultaRepository.existsByVeterinarioIdAndDataHoraAndIdNot(
                veterinarioId,
                dataHora,
                consultaId);
        }

        if (conflito) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflito de agenda");
        }
    }
}
