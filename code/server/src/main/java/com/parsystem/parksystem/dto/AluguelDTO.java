package com.parsystem.parksystem.dto;

import com.parsystem.parksystem.model.Cliente;
import com.parsystem.parksystem.model.Veiculo;

public record AluguelDTO(Long idAluguel, Veiculo idveiculo, Cliente idcliente, Double valor, String data) {
}
