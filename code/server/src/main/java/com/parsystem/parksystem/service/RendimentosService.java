package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.RendimentosDTO;
import com.parsystem.parksystem.model.Rendimentos;
import com.parsystem.parksystem.repository.RendimentosRepository;

@Service
public class RendimentosService {

    @Autowired
    private RendimentosRepository rendimentosRepository;

    public List<RendimentosDTO> listarTodos() {
        return rendimentosRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RendimentosDTO criarRendimentos(RendimentosDTO rendimentosDTO) {
        Rendimentos rendimentos = toEntity(rendimentosDTO);
        rendimentosRepository.save(rendimentos);
        return toDTO(rendimentos);
    }

    public RendimentosDTO buscarPorId(Long id) {
        Rendimentos rendimentos = rendimentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendimentos não encontrados"));
        return toDTO(rendimentos);
    }

    public RendimentosDTO atualizarRendimentos(Long id, RendimentosDTO rendimentosDTO) {
        Rendimentos rendimentos = rendimentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendimentos não encontrados"));
        rendimentos.setValor(rendimentosDTO.valor());
        return toDTO(rendimentos);
    }

    public void deletarRendimentos(Long id) {
        rendimentosRepository.deleteById(id);
    }

    private RendimentosDTO toDTO(Rendimentos rendimentos) {
        return new RendimentosDTO(rendimentos.getIdRendimentos(), rendimentos.getValor());
    }

    private Rendimentos toEntity(RendimentosDTO rendimentosDTO) {
        Rendimentos rendimentos = new Rendimentos();
        rendimentos.setValor(rendimentosDTO.valor());
        return rendimentos;
    }
}
