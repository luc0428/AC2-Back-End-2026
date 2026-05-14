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

import com.example.petcare.model.VeterinarioModel;
import com.example.petcare.service.VeterinarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Veterinario")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping("/ReadVeterinario")
    public List<VeterinarioModel> listarVeterinarios() {
        return veterinarioService.ReadAll();
    }

    @GetMapping("/ReadVeterinarioId/{id}")
    public VeterinarioModel ReadById(@PathVariable Long id) {
        return veterinarioService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeterinarioModel created(@RequestBody VeterinarioModel veterinario) {
        return veterinarioService.Create(veterinario);
    }

    @PutMapping("/UpdateVeterinario/{id}")
    public VeterinarioModel update(@PathVariable Long id, @RequestBody VeterinarioModel veterinarioAtualizado) {
        return veterinarioService.Update(id, veterinarioAtualizado);
    }

    @DeleteMapping("/DeleteVeterinario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerVeterinario(@PathVariable Long id) {
        veterinarioService.Delete(id);
    }
}
