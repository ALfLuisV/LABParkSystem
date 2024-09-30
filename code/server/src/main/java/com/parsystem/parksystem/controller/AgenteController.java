package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.parsystem.parksystem.dto.AgenteDTO;
import com.parsystem.parksystem.service.AgenteService;


@RestController
@RequestMapping("/agentes")
public class AgenteController{
    @Autowired
    private AgenteService agenteService;

    @GetMapping
    public List<AgenteDTO> listarTodos(){
        return agenteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<AgenteDTO> criarAgente(@RequestBody AgenteDTO agenteDTO){
        return ResponseEntity.ok(agenteService.buscarPorCnpj(null));
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<AgenteDTO> atualizarAgente(@PathVariable String cnpj, @RequestBody AgenteDTO agenteDTO){
        return ResponseEntity.ok(agenteService.atualizarAgente(cnpj, agenteDTO));
    }

    // @GetMapping("/{cnpj}")
    // public ResponseEntity<AgenteDTO> buscarPorCnpj(@PathVariable String cnpj){
    //     return ResponseEntity.ok(agenteService.buscarPorCnpj( ));
    // }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletarAgente(@PathVariable String cnpj){
        agenteService.deletarAgente(cnpj);
        return ResponseEntity.noContent().build();
    }
}

