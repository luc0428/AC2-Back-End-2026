package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.TutorModel;

public interface TutorRepository extends JpaRepository<TutorModel, Long> {

}
