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

import com.example.petcare.model.VacinacaoModel;
import com.example.petcare.service.VacinacaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Vacinacao")
public class VacinacaoController {

    private final VacinacaoService vacinacaoService;

    public VacinacaoController(VacinacaoService vacinacaoService) {
        this.vacinacaoService = vacinacaoService;
    }

    @GetMapping("/ReadVacinacao")
    public List<VacinacaoModel> listarVacinacoes() {
        return vacinacaoService.ReadAll();
    }

    @GetMapping("/ReadVacinacaoId/{id}")
    public VacinacaoModel ReadById(@PathVariable Long id) {
        return vacinacaoService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacinacaoModel created(@RequestBody VacinacaoModel vacinacao) {
        return vacinacaoService.Create(vacinacao);
    }

    @PutMapping("/UpdateVacinacao/{id}")
    public VacinacaoModel update(@PathVariable Long id, @RequestBody VacinacaoModel vacinacaoAtualizada) {
        return vacinacaoService.Update(id, vacinacaoAtualizada);
    }

    @DeleteMapping("/DeleteVacinacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerVacinacao(@PathVariable Long id) {
        vacinacaoService.Delete(id);
    }
}
