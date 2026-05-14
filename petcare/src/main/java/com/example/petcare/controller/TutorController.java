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

import com.example.petcare.model.TutorModel;
import com.example.petcare.service.TutorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Tutor")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping("/ReadTutor")
    public List<TutorModel> listarTutores() {
        return tutorService.ReadAll();
    }

    @GetMapping("/ReadTutorId/{id}")
    public TutorModel ReadById(@PathVariable Long id) {
        return tutorService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TutorModel created(@RequestBody TutorModel tutor) {
        return tutorService.Create(tutor);
    }

    @PutMapping("/UpdateTutor/{id}")
    public TutorModel update(@PathVariable Long id, @RequestBody TutorModel tutorAtualizado) {
        return tutorService.Update(id, tutorAtualizado);
    }

    @DeleteMapping("/DeleteTutor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerTutor(@PathVariable Long id) {
        tutorService.Delete(id);
    }
}
