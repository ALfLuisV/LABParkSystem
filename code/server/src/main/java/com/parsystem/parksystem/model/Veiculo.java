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
@Table(name = "veiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idveiculo;

    @Column(unique = true, nullable = false)
    private String placa;

    private String montadora;

    private int ano;

    private String modelo;

    @Column(nullable = false)
    public boolean disponivel;

    private float diaria;
}
