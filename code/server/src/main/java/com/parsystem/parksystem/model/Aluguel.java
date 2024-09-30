package com.parsystem.parksystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aluguel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluguel;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "idveiculo")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;
}
