package com.projectodonto.checkpointjava.repository;

import com.projectodonto.checkpointjava.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
