package com.projectodonto.checkpointjava.repository;

import com.projectodonto.checkpointjava.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
