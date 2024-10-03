package com.parsystem.parksystem.dto;

import com.parsystem.parksystem.model.Agente;
import com.parsystem.parksystem.model.Banco;

public record AgenteBancoDTO(Long idAgenteBanco, Long  idagente, String  codigoBanco) {
}
