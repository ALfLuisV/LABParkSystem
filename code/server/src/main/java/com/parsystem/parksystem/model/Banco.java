package com.parsystem.parksystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idbanco;

    @Column
    private String codigoBanco;

    @Column(nullable = false)
    private String porte;

    public Banco(int id) {
        this.idbanco = (long) id;
    }
}
