package com.example.petcare.controller;

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

import com.example.petcare.model.AnimalModel;
import com.example.petcare.service.AnimalService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/ReadAnimal")
    public List<AnimalModel> listarAnimais() {
        return animalService.ReadAll();
    }

    @GetMapping("/ReadAnimalId/{id}")
    public AnimalModel ReadById(@PathVariable Long id) {
        return animalService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalModel created(@RequestBody AnimalModel animal) {
        return animalService.Create(animal);
    }

    @PutMapping("/UpdateAnimal/{id}")
    public AnimalModel update(@PathVariable Long id, @RequestBody AnimalModel animalAtualizado) {
        return animalService.Update(id, animalAtualizado);
    }

    @DeleteMapping("/DeleteAnimal/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAnimal(@PathVariable Long id) {
        animalService.Delete(id);
    }
}
