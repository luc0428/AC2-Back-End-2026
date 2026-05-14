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

import com.example.petcare.model.ProntuarioModel;
import com.example.petcare.service.ProntuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Prontuario")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping("/ReadProntuario")
    public List<ProntuarioModel> listarProntuarios() {
        return prontuarioService.ReadAll();
    }

    @GetMapping("/ReadProntuarioId/{id}")
    public ProntuarioModel ReadById(@PathVariable Long id) {
        return prontuarioService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProntuarioModel created(@RequestBody ProntuarioModel prontuario) {
        return prontuarioService.Create(prontuario);
    }

    @PutMapping("/UpdateProntuario/{id}")
    public ProntuarioModel update(@PathVariable Long id, @RequestBody ProntuarioModel prontuarioAtualizado) {
        return prontuarioService.Update(id, prontuarioAtualizado);
    }

    @DeleteMapping("/DeleteProntuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProntuario(@PathVariable Long id) {
        prontuarioService.Delete(id);
    }
}
