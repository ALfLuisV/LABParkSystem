package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parsystem.parksystem.dto.AgenteBancoDTO;
import com.parsystem.parksystem.service.AgenteBancoService;

@RestController
@RequestMapping("/agente-banco")
@CrossOrigin(origins = "http://localhost:3000")
public class AgenteBancoController {

    @Autowired
    private AgenteBancoService agenteBancoService;

    @GetMapping
    public List<AgenteBancoDTO> listarTodos() {
        return agenteBancoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<AgenteBancoDTO> criarAgenteBanco(@RequestBody AgenteBancoDTO agenteBancoDTO) {
        return ResponseEntity.ok(agenteBancoService.criarAgenteBanco(agenteBancoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteBancoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agenteBancoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenteBancoDTO> atualizarAgenteBanco(@PathVariable Long id, @RequestBody AgenteBancoDTO agenteBancoDTO) {
        return ResponseEntity.ok(agenteBancoService.atualizarAgenteBanco(id, agenteBancoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgenteBanco(@PathVariable Long id) {
        agenteBancoService.deletarAgenteBanco(id);
        return ResponseEntity.noContent().build();
    }
}
