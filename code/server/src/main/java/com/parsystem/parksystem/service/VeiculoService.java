package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.VeiculoDTO;
import com.parsystem.parksystem.model.Veiculo;
import com.parsystem.parksystem.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veicRepository;

    public List<VeiculoDTO> listarTodos() {
        return veicRepository.findAll().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public VeiculoDTO criarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = toEntity(veiculoDTO);
        veicRepository.save(veiculo);
        return toDTO(veiculo);
    }

    public VeiculoDTO buscarPorId(Long id) {
        Veiculo veic = veicRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
        return toDTO(veic);
    }

    public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veicDTO) {
        Veiculo veic = veicRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
        veic.setPlaca(veicDTO.placa());
        veic.setMontadora(veicDTO.montadora());
        veic.setModelo(veicDTO.modelo());
        veic.setDisponivel(veicDTO.disponivel());
        veic.setAno(veicDTO.ano());
        veic.setDiaria(veicDTO.diaria());
        return toDTO(veic);
    }

    public void deletarVeiculo(long id) {
        veicRepository.deleteById(id);
    }

    private VeiculoDTO toDTO(Veiculo veic) {
        return new VeiculoDTO(
                veic.getIdveiculo(),
                veic.getPlaca(),
                veic.getMontadora(),
                veic.getAno(),
                veic.getModelo(),
                veic.isDisponivel(),
                veic.getDiaria()
                );
    }

    private Veiculo toEntity(VeiculoDTO veicDTO) {
        Veiculo veic = new Veiculo();
        veic.setIdveiculo(veicDTO.idveiculo());
        veic.setPlaca(veicDTO.placa());
        veic.setMontadora(veicDTO.montadora());
        veic.setModelo(veicDTO.modelo());
        veic.setDisponivel(veicDTO.disponivel());
        veic.setAno(veicDTO.ano());
        veic.setDiaria(veicDTO.diaria());
        return veic;
    }
}
