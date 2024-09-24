package com.parsystem.parksystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data  // Lombok - Gera automaticamente getters, setters, equals, hashCode e toString
@AllArgsConstructor // Lombok - Gera construtor com todos os campos
@NoArgsConstructor  // Lombok - Gera construtor sem argumentos
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcliente;

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    private String profissao;

    private Long idendereco;
}