package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.ProntuarioModel;

public interface ProntuarioRepository extends JpaRepository<ProntuarioModel, Long> {

}
