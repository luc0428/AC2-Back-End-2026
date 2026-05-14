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

import com.example.petcare.model.ConsultaModel;
import com.example.petcare.service.ConsultaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/ReadConsulta")
    public List<ConsultaModel> listarConsultas() {
        return consultaService.ReadAll();
    }

    @GetMapping("/ReadConsultaId/{id}")
    public ConsultaModel ReadById(@PathVariable Long id) {
        return consultaService.ReadById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaModel created(@RequestBody ConsultaModel consulta) {
        return consultaService.Create(consulta);
    }

    @PutMapping("/UpdateConsulta/{id}")
    public ConsultaModel update(@PathVariable Long id, @RequestBody ConsultaModel consultaAtualizada) {
        return consultaService.Update(id, consultaAtualizada);
    }

    @DeleteMapping("/DeleteConsulta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerConsulta(@PathVariable Long id) {
        consultaService.Delete(id);
    }
}
