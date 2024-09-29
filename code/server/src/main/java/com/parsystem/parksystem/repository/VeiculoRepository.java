package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.Veiculo;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
}
