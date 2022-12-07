package com.example.clinicadentista.repository;

import com.example.clinicadentista.model.DentistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<DentistaModel,Integer> {

}
