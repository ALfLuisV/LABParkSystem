package com.parsystem.parksystem.model;

import io.micrometer.common.lang.Nullable;
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
    private Long idaluguel;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "idveiculo")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idcredito")
    @Nullable
    private Credito credito;

    public Aluguel(int id) {
        this.idaluguel = (long) id;
    }
}
