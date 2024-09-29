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
@Data  // Lombok - Gera automaticamente getters, setters, equals, hashCode e toString
@AllArgsConstructor // Lombok - Gera construtor com todos os campos
@NoArgsConstructor  // Lombok - Gera construtor sem argumentos

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeic;

    @Column(unique = true, nullable = false)
    private String placa;

    private String montadora;

    private int ano;

    private String modelo;

    @Column(nullable = false)
    public boolean disponivel;
}
