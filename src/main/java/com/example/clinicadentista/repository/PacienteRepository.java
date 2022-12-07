package com.example.clinicadentista.repository;

import com.example.clinicadentista.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel,Integer> {

}
