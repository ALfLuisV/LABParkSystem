package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parsystem.parksystem.dto.AluguelDTO;
import com.parsystem.parksystem.service.AluguelService;

@RestController
@RequestMapping("/Alugueis")
public class AluguelController {

    @Autowired
    private AluguelService AluguelService;

    @GetMapping
    public List<AluguelDTO> listarTodos() {
        return AluguelService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<AluguelDTO> criarAluguel(@RequestBody AluguelDTO AluguelDTO) {
        return ResponseEntity.ok(AluguelService.criarAluguel(AluguelDTO));
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
