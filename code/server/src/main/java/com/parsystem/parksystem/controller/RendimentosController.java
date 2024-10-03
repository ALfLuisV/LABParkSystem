package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parsystem.parksystem.dto.RendimentosDTO;
import com.parsystem.parksystem.service.RendimentosService;

@RestController
@RequestMapping("/rendimentos")
@CrossOrigin(origins = "http://localhost:3000")
public class RendimentosController {

    @Autowired
    private RendimentosService rendimentosService;

    @GetMapping
    public List<RendimentosDTO> listarTodos() {
        return rendimentosService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<RendimentosDTO> criarRendimentos(@RequestBody RendimentosDTO rendimentosDTO) {
        return ResponseEntity.ok(rendimentosService.criarRendimentos(rendimentosDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendimentosDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rendimentosService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendimentosDTO> atualizarRendimentos(@PathVariable Long id, @RequestBody RendimentosDTO rendimentosDTO) {
        return ResponseEntity.ok(rendimentosService.atualizarRendimentos(id, rendimentosDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRendimentos(@PathVariable Long id) {
        rendimentosService.deletarRendimentos(id);
        return ResponseEntity.noContent().build();
    }
}
