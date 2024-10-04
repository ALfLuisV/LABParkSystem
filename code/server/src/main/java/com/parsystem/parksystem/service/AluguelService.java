package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.AluguelDTO;
import com.parsystem.parksystem.model.Aluguel;
import com.parsystem.parksystem.model.Cliente;
import com.parsystem.parksystem.model.Veiculo;
import com.parsystem.parksystem.repository.AluguelRepository;
import com.parsystem.parksystem.repository.ClienteRepository;
import com.parsystem.parksystem.repository.VeiculoRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<AluguelDTO> listarTodos() {
        return aluguelRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AluguelDTO criarAluguel(AluguelDTO aluguelDTO) {

        Aluguel aluguel = toEntity(aluguelDTO);
        System.out.println("AluguelDTO RECEBIDO NA CRIAR ALUGUEL::::::::::::::::::::::::");
        System.out.println(aluguel);
        aluguelRepository.save(aluguel);
        return toDTO(aluguel);
    }

    public AluguelDTO buscarPorId(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        return toDTO(aluguel);
    }

    public List<AluguelDTO> buscaPorClientId(Long idcliente) {
        List<Aluguel> rents = aluguelRepository.findByClienteIdcliente(idcliente); // Assumindo que esse método retorna List<Aluguel>

        if (rents.isEmpty()) {
            throw new RuntimeException("Alugueis não encontrados");
        }

        return rents.stream()
                .map(this::toDTO) // Convertendo Aluguel para AluguelDTO
                .collect(Collectors.toList());
    }


    public List<AluguelDTO> buscaPorStatus(String status) {
        List<Aluguel> rents = aluguelRepository.findByStatus(status); // Assumindo que esse método retorna List<Aluguel>

        if (rents.isEmpty()) {
            throw new RuntimeException("Alugueis não encontrados");
        }

        return rents.stream()
                .map(this::toDTO) // Convertendo Aluguel para AluguelDTO
                .collect(Collectors.toList());
    }

    public AluguelDTO atualizarAluguel(Long id, AluguelDTO aluguelDTO) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        System.out.println(aluguel);
        aluguel.setValor(aluguelDTO.valor());
        aluguel.setData(aluguelDTO.data());
        aluguel.setStatus(aluguelDTO.status());
        System.out.println(aluguel);
        aluguelRepository.save(aluguel);
        return toDTO(aluguel);
    }

    public void deletarAluguel(Long id) {
        aluguelRepository.deleteById(id);
    }

    private AluguelDTO toDTO(Aluguel aluguel) {
        return new AluguelDTO(
                aluguel.getIdaluguel(),
                aluguel.getVeiculo(), // Alterado para obter o ID do Veiculo
                aluguel.getCliente(), // Alterado para obter o ID do Cliente
                aluguel.getValor(),
                aluguel.getData(),
                aluguel.getStatus());
    }

    private Aluguel toEntity(AluguelDTO aluguelDTO) {
        Aluguel aluguel = new Aluguel();
        aluguel.setIdaluguel(aluguelDTO.idaluguel());
        // Aqui você precisa obter as entidades Veiculo e Cliente a partir de seus
        // repositórios
        Veiculo veiculo = veiculoRepository.findById(aluguelDTO.idveiculo().getIdveiculo())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        Cliente cliente = clienteRepository.findById(aluguelDTO.idcliente().getIdcliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        aluguel.setVeiculo(veiculo); // Agora define a entidade Veiculo
        aluguel.setCliente(cliente); // Agora define a entidade Cliente
        aluguel.setValor(aluguelDTO.valor());
        aluguel.setData(aluguelDTO.data());
        aluguel.setStatus(aluguelDTO.status());
        System.out.println("ALUGUEL CRIADO NA ENTITY:::::::::::::::::::::::::::::::::::::::");
        System.out.println(aluguel);
        return aluguel;
    }

}
