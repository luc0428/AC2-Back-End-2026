package com.example.petcare.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.petcare.model.AnimalModel;
import com.example.petcare.model.TutorModel;
import com.example.petcare.repository.AnimalRepository;
import com.example.petcare.repository.TutorRepository;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    public AnimalService(AnimalRepository animalRepository, TutorRepository tutorRepository) {
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
    }

    public List<AnimalModel> ReadAll() {
        return animalRepository.findAll();
    }

    public AnimalModel ReadById(Long id) {
        return animalRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal nao encontrado"));
    }

    public AnimalModel Create(AnimalModel animal) {
        animal.setId(null);
        TutorModel tutor = validarTutor(animal);
        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    public AnimalModel Update(Long id, AnimalModel animalAtualizado) {
        AnimalModel animalExistente = ReadById(id);
        TutorModel tutor = validarTutor(animalAtualizado);

        animalExistente.setNome(animalAtualizado.getNome());
        animalExistente.setEspecie(animalAtualizado.getEspecie());
        animalExistente.setRaca(animalAtualizado.getRaca());
        animalExistente.setDataNascimento(animalAtualizado.getDataNascimento());
        animalExistente.setTutor(tutor);

        return animalRepository.save(animalExistente);
    }

    public void Delete(Long id) {
        AnimalModel animalExistente = ReadById(id);
        animalRepository.delete(animalExistente);
    }

    private TutorModel validarTutor(AnimalModel animal) {
        if (animal.getTutor() == null || animal.getTutor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tutor obrigatorio");
        }

        return tutorRepository.findById(animal.getTutor().getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor nao encontrado"));
    }
}
