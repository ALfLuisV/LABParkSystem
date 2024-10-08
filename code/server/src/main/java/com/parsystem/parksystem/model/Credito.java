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
    private Long idcredito;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer parcelas;

    // @ManyToOne
    @JoinColumn(name = "idbanco")
    private Long  idbanco;

    public Credito(int id) {
        this.idcredito = (long) id;
    }
}
