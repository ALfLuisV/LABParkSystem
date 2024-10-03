package com.parsystem.parksystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsystem.parksystem.dto.ClienteDTO;
import com.parsystem.parksystem.model.Cliente;
import com.parsystem.parksystem.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = toEntity(clienteDTO);
        clienteRepository.save(cliente);
        return toDTO(cliente);
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return toDTO(cliente);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDTO.nome());
        cliente.setCpf(clienteDTO.cpf());
        cliente.setRg(clienteDTO.rg());
        cliente.setProfissao(clienteDTO.profissao());
        clienteRepository.save(cliente);
        return toDTO(cliente);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    
    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
            cliente.getIdcliente(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getRg(),
            cliente.getProfissao()
        );
    }

    private Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setIdcliente(clienteDTO.idcliente());
        cliente.setNome(clienteDTO.nome());
        cliente.setCpf(clienteDTO.cpf());
        cliente.setRg(clienteDTO.rg());
        cliente.setProfissao(clienteDTO.profissao());
        return cliente;
    }
}
