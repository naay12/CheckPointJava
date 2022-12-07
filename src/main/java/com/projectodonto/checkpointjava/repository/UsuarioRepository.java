package com.projectodonto.checkpointjava.repository;

import com.projectodonto.checkpointjava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
