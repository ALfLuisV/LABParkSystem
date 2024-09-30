package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.EnderecoDTO;
import com.parsystem.parksystem.model.Endereco;
import com.parsystem.parksystem.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> listarTodos() {
        return enderecoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = toEntity(enderecoDTO);
        enderecoRepository.save(endereco);
        return toDTO(endereco);
    }

    public EnderecoDTO buscarPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return toDTO(endereco);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        endereco.setRua(enderecoDTO.rua());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setPais(enderecoDTO.pais());
        return toDTO(endereco);
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    private EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(endereco.getIdEndereco(), endereco.getRua(), endereco.getCidade(), endereco.getEstado(), endereco.getPais());
    }

    private Endereco toEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.rua());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setPais(enderecoDTO.pais());
        return endereco;
    }
}
