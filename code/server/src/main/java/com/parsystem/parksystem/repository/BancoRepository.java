package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
}
