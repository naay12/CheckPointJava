package com.example.medicamento1.repository;

import com.example.medicamento1.model.DentistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<DentistaModel,Integer> {

}
