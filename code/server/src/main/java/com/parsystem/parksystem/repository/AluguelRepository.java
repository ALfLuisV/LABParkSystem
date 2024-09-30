package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}
