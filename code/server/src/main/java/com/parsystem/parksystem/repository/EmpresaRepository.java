package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
