package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parsystem.parksystem.dto.CreditoDTO;
import com.parsystem.parksystem.service.CreditoService;

@RestController
@RequestMapping("/creditos")
@CrossOrigin(origins = "http://localhost:3000")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping
    public List<CreditoDTO> listarTodos() {
        return creditoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<CreditoDTO> criarCredito(@RequestBody CreditoDTO creditoDTO) {
        // Log para verificar a chamada do método
        System.out.println("Controlador chamado com DTO: " + creditoDTO);

        // Chama o serviço para criar o crédito
        CreditoDTO retornoCreditoDTO = creditoService.criarCredito(creditoDTO);

        // Verificar se o serviço retornou algo
        System.out.println("DTO recebido do serviço: " + retornoCreditoDTO);

        // Retornar a resposta
        return ResponseEntity.ok(retornoCreditoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(creditoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditoDTO> atualizarCredito(@PathVariable Long id, @RequestBody CreditoDTO creditoDTO) {
        return ResponseEntity.ok(creditoService.atualizarCredito(id, creditoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCredito(@PathVariable Long id) {
        creditoService.deletarCredito(id);
        return ResponseEntity.noContent().build();
    }
}
