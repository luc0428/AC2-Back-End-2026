package com.example.petcare.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.petcare.model.TutorModel;
import com.example.petcare.repository.TutorRepository;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<TutorModel> ReadAll() {
        return tutorRepository.findAll();
    }

    public TutorModel ReadById(Long id) {
        return tutorRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor nao encontrado"));
    }

    public TutorModel Create(TutorModel tutor) {
        tutor.setId(null);
        return tutorRepository.save(tutor);
    }

    public TutorModel Update(Long id, TutorModel tutorAtualizado) {
        TutorModel tutorExistente = ReadById(id);
        tutorExistente.setNome(tutorAtualizado.getNome());
        tutorExistente.setTelefone(tutorAtualizado.getTelefone());
        tutorExistente.setEmail(tutorAtualizado.getEmail());

        return tutorRepository.save(tutorExistente);
    }

    public void Delete(Long id) {
        TutorModel tutorExistente = ReadById(id);
        tutorRepository.delete(tutorExistente);
    }
}
