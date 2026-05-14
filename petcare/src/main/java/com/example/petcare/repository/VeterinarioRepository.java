package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.VeterinarioModel;

public interface VeterinarioRepository extends JpaRepository<VeterinarioModel, Long> {

}
