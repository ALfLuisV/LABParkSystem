package com.parsystem.parksystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredito;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private Integer parcelas;

    @ManyToOne
    @JoinColumn(name = "idbanc")
    private Banco banco;

    @ManyToOne
    @JoinColumn(name = "idalugue")
    private Aluguel aluguel;
}
