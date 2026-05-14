package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.VacinacaoModel;

public interface VacinacaoRepository extends JpaRepository<VacinacaoModel, Long> {

}
