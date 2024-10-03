package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.EmpresaDTO;
import com.parsystem.parksystem.model.Empresa;
import com.parsystem.parksystem.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<EmpresaDTO> listarTodos() {
        return empresaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = toEntity(empresaDTO);
        empresaRepository.save(empresa);
        return toDTO(empresa);
    }

    public EmpresaDTO buscarPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return toDTO(empresa);
    }

    public EmpresaDTO atualizarEmpresa(Long id, EmpresaDTO empresaDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        empresa.setCpfResponsavel(empresaDTO.cpfResponsavel());
        return toDTO(empresa);
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    private EmpresaDTO toDTO(Empresa empresa) {
        return new EmpresaDTO(empresa.getIdempresa(), empresa.getCpfResponsavel());
    }

    private Empresa toEntity(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setCpfResponsavel(empresaDTO.cpfResponsavel());
        return empresa;
    }
}
