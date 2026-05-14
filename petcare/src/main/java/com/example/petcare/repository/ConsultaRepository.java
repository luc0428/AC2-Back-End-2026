package com.example.petcare.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.ConsultaModel;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {

    boolean existsByVeterinarioIdAndDataHora(Long veterinarioId, LocalDateTime dataHora);

    boolean existsByVeterinarioIdAndDataHoraAndIdNot(Long veterinarioId, LocalDateTime dataHora, Long id);
}
