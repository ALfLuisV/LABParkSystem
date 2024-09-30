package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.AluguelDTO;
import com.parsystem.parksystem.model.Aluguel;
import com.parsystem.parksystem.repository.AluguelRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<AluguelDTO> listarTodos() {
        return aluguelRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AluguelDTO criarAluguel(AluguelDTO aluguelDTO) {
        Aluguel aluguel = toEntity(aluguelDTO);
        aluguelRepository.save(aluguel);
        return toDTO(aluguel);
    }

    public AluguelDTO buscarPorId(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        return toDTO(aluguel);
    }

    public AluguelDTO atualizarAluguel(Long id, AluguelDTO aluguelDTO) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        aluguel.setValor(aluguelDTO.valor());
        aluguel.setDate(aluguelDTO.date());
        return toDTO(aluguel);
    }

    public void deletarAluguel(Long id) {
        aluguelRepository.deleteById(id);
    }

    private AluguelDTO toDTO(Aluguel aluguel) {
        return new AluguelDTO(aluguel.getIdAluguel(), aluguel.getValor(), aluguel.getDate());
    }

    private Aluguel toEntity(AluguelDTO aluguelDTO) {
        Aluguel aluguel = new Aluguel();
        aluguel.setValor(aluguelDTO.valor());
        aluguel.setDate(aluguelDTO.date());
        return aluguel;
    }
}
