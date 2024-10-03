package com.parsystem.parksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.parsystem.parksystem.dto.VeiculoDTO;
import com.parsystem.parksystem.service.VeiculoService;


@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "http://localhost:3000")
public class VeiculoController {
    
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<VeiculoDTO> listarTodos(){
        return veiculoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> criarVeiculo(@RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok(veiculoService.criarVeiculo(veiculoDTO));
    }

    @GetMapping("/id")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }


    @PutMapping("/id")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok(veiculoService.atualizarVeiculo(id, veiculoDTO));
    }

    @DeleteMapping("/id")
    public ResponseEntity<VeiculoDTO> deleteVeiculo(@PathVariable long id){
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }

}
