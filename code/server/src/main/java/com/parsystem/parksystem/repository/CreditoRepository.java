package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.Credito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
}
