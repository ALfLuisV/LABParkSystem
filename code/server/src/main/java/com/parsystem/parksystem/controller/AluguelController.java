package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parsystem.parksystem.dto.AluguelDTO;
import com.parsystem.parksystem.service.AluguelService;

@RestController
@RequestMapping("/alugueis")
@CrossOrigin(origins = "http://localhost:3000")
public class AluguelController {

    @Autowired
    private AluguelService AluguelService;

    @GetMapping
    public List<AluguelDTO> listarTodos() {
        return AluguelService.listarTodos();
    }

    @GetMapping("/{idcliente}")
    public List<AluguelDTO> listarPorCliente(@PathVariable Long idcliente){ 
        // System.out.println("CONTROLLER AQUIIIII:::::::::::::::::::::::::::::::::::::");
        return AluguelService.buscaPorClientId(idcliente);
    }

    @GetMapping("/status/{status}")
    public List<AluguelDTO> listarPorCliente(@PathVariable String status){ 
        return AluguelService.buscaPorStatus(status);
    }

    @PostMapping
    public ResponseEntity<AluguelDTO> criarAluguel(@RequestBody AluguelDTO aluguelDTO) {
        return ResponseEntity.ok(AluguelService.criarAluguel(aluguelDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AluguelDTO> atualizarAluguel(@PathVariable Long id, @RequestBody AluguelDTO AluguelDTO) {
        return ResponseEntity.ok(AluguelService.atualizarAluguel(id, AluguelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluguel(@PathVariable Long id) {
        AluguelService.deletarAluguel(id);
        return ResponseEntity.noContent().build();
    }
}
