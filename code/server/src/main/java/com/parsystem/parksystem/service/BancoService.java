package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.BancoDTO;
import com.parsystem.parksystem.model.Banco;
import com.parsystem.parksystem.repository.BancoRepository;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public List<BancoDTO> listarTodos() {
        return bancoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BancoDTO criarBanco(BancoDTO bancoDTO) {
        Banco banco = toEntity(bancoDTO);
        bancoRepository.save(banco);
        return toDTO(banco);
    }

    public BancoDTO buscarPorId(Long id) {
        Banco banco = bancoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco não encontrado"));
        return toDTO(banco);
    }

    public BancoDTO atualizarBanco(Long id, BancoDTO bancoDTO) {
        Banco banco = bancoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco não encontrado"));
        banco.setCodigoBanco(bancoDTO.codigoBanco());
        banco.setPorte(bancoDTO.porte());
        return toDTO(banco);
    }

    public void deletarBanco(Long id) {
        bancoRepository.deleteById(id);
    }

    private BancoDTO toDTO(Banco banco) {
        return new BancoDTO(banco.getIdbanco(), banco.getCodigoBanco(), banco.getPorte());
    }

    private Banco toEntity(BancoDTO bancoDTO) {
        Banco banco = new Banco();
        banco.setCodigoBanco(bancoDTO.codigoBanco());
        banco.setPorte(bancoDTO.porte());
        return banco;
    }
}
