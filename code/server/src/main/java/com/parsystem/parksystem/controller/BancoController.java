package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parsystem.parksystem.dto.BancoDTO;
import com.parsystem.parksystem.service.BancoService;

@RestController
@RequestMapping("/bancos")
@CrossOrigin(origins = "http://localhost:3000")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public List<BancoDTO> listarTodos() {
        return bancoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<BancoDTO> criarBanco(@RequestBody BancoDTO bancoDTO) {
        return ResponseEntity.ok(bancoService.criarBanco(bancoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bancoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoDTO> atualizarBanco(@PathVariable Long id, @RequestBody BancoDTO bancoDTO) {
        return ResponseEntity.ok(bancoService.atualizarBanco(id, bancoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBanco(@PathVariable Long id) {
        bancoService.deletarBanco(id);
        return ResponseEntity.noContent().build();
    }
}
