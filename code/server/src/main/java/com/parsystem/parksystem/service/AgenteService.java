package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.AgenteDTO;
import com.parsystem.parksystem.model.Agente;
import com.parsystem.parksystem.repository.AgenteRepository;

@Service
public class AgenteService {

    @Autowired
    private AgenteRepository agenteRepository;

    public List<AgenteDTO> listarTodos() {
        return agenteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AgenteDTO criarAgente(AgenteDTO agenteDTO) {
        System.out.println(agenteDTO);
        Agente agente = toEntity(agenteDTO);
        agenteRepository.save(agente);
        return toDTO(agente);
    }

    public AgenteDTO buscarPorCnpj(Long idagente) {
        Agente agente = agenteRepository.findById(idagente)
                .orElseThrow(() -> new RuntimeException("Agente não encontrado"));
        return toDTO(agente);
    }

    public AgenteDTO atualizarAgente(Long idagente, AgenteDTO agenteDTO) {
        Agente agente = agenteRepository.findById(idagente)
                .orElseThrow(() -> new RuntimeException("Agente não encontrado"));
        agente.setIdagente(agenteDTO.idagente());
        agente.setCnpj(agenteDTO.cnpj());
        agente.setEmail(agenteDTO.email());
        agente.setNome(agenteDTO.nome());
        agente.setTelefone(agenteDTO.telefone());
        agente.setIdendereco(agenteDTO.idendereco());
        agente.setTipoAgente(agenteDTO.tipoAgente());
        return toDTO(agente);
    }

    public void deletarAgente(Long idagente) {
        agenteRepository.deleteById(idagente);
    }

    private AgenteDTO toDTO(Agente agente) {
        return new AgenteDTO(
            agente.getIdagente(),
            agente.getCnpj(), 
            agente.getNome(),
            agente.getTelefone(),
            agente.getEmail(),
            agente.getIdendereco(),
            agente.getTipoAgente()
        );
    }

    private Agente toEntity(AgenteDTO agenteDTO) {
        Agente agente = new Agente();
        agente.setIdagente(agenteDTO.idagente());
        agente.setCnpj(agenteDTO.cnpj());
        agente.setNome((agenteDTO.nome()));
        agente.setTelefone(agenteDTO.telefone());
        agente.setEmail(agenteDTO.email());
        agente.setIdendereco(agenteDTO.idendereco());
        agente.setTipoAgente(agenteDTO.tipoAgente());
        return agente;
    }
}