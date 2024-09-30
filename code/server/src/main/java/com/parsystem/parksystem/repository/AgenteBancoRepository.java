package com.parsystem.parksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parsystem.parksystem.model.AgenteBanco;

@Repository
public interface AgenteBancoRepository extends JpaRepository<AgenteBanco, Long> {
}
