package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.CreditoDTO;
import com.parsystem.parksystem.model.Credito;
import com.parsystem.parksystem.repository.CreditoRepository;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    public List<CreditoDTO> listarTodos() {
        return creditoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CreditoDTO criarCredito(CreditoDTO creditoDTO) {
        Credito credito = toEntity(creditoDTO);
        creditoRepository.save(credito);
        return toDTO(credito);
    }

    public CreditoDTO buscarPorId(Long id) {
        Credito credito = creditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
        return toDTO(credito);
    }

    public CreditoDTO atualizarCredito(Long id, CreditoDTO creditoDTO) {
        Credito credito = creditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
        credito.setValor(creditoDTO.valor());
        credito.setParcelas(creditoDTO.parcelas());
        return toDTO(credito);
    }

    public void deletarCredito(Long id) {
        creditoRepository.deleteById(id);
    }

    private CreditoDTO toDTO(Credito credito) {
        return new CreditoDTO(credito.getIdcredito(), credito.getValor(), credito.getParcelas());
    }

    private Credito toEntity(CreditoDTO creditoDTO) {
        Credito credito = new Credito();
        credito.setValor(creditoDTO.valor());
        credito.setParcelas(creditoDTO.parcelas());
        return credito;
    }
}
