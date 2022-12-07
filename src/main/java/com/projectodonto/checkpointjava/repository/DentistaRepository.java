package com.projectodonto.checkpointjava.repository;

import com.projectodonto.checkpointjava.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
