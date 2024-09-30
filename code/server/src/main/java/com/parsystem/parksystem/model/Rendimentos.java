package com.parsystem.parksystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rendimentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rendimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRendimentos;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;
}
