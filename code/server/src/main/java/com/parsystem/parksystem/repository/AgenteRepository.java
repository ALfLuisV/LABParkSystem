package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parsystem.parksystem.model.Agente;


@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long>{
    
}
