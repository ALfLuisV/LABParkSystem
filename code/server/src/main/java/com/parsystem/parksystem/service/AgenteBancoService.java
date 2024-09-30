package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.AgenteBancoDTO;
import com.parsystem.parksystem.model.AgenteBanco;
import com.parsystem.parksystem.repository.AgenteBancoRepository;

@Service
public class AgenteBancoService {

    @Autowired
    private AgenteBancoRepository agenteBancoRepository;

    public List<AgenteBancoDTO> listarTodos() {
        return agenteBancoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AgenteBancoDTO criarAgenteBanco(AgenteBancoDTO agenteBancoDTO) {
        AgenteBanco agenteBanco = toEntity(agenteBancoDTO);
        agenteBancoRepository.save(agenteBanco);
        return toDTO(agenteBanco);
    }

    public AgenteBancoDTO buscarPorId(Long id) {
        AgenteBanco agenteBanco = agenteBancoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AgenteBanco não encontrado"));
        return toDTO(agenteBanco);
    }

    public AgenteBancoDTO atualizarAgenteBanco(Long id, AgenteBancoDTO agenteBancoDTO) {
        AgenteBanco agenteBanco = agenteBancoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AgenteBanco não encontrado"));
        agenteBanco.setCodigoBanco(agenteBancoDTO.codigoBanco());
        return toDTO(agenteBanco);
    }

    public void deletarAgenteBanco(Long id) {
        agenteBancoRepository.deleteById(id);
    }

    private AgenteBancoDTO toDTO(AgenteBanco agenteBanco) {
        return new AgenteBancoDTO(agenteBanco.getIdAgenteBanco(), agenteBanco.getCodigoBanco());
    }

    private AgenteBanco toEntity(AgenteBancoDTO agenteBancoDTO) {
        AgenteBanco agenteBanco = new AgenteBanco();
        agenteBanco.setCodigoBanco(agenteBancoDTO.codigoBanco());
        return agenteBanco;
    }
}
