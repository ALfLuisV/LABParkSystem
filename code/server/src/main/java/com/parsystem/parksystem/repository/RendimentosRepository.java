package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.Rendimentos;

@Repository
public interface RendimentosRepository extends JpaRepository<Rendimentos, Long> {
}
