package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.AnimalModel;

public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {

}
