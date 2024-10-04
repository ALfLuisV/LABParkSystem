package com.parsystem.parksystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "agente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Gera o ID automaticamente
    private Long idagente;

    @Column(nullable = false)
    private String cnpj;


    @Column(nullable = false)
    private String nome;

    private String telefone;

    private String email;

    @JoinColumn(name = "idendereco")
    // @ManyToOne(fetch = FetchType.LAZY)
    private Long  idendereco;

    @Column(name = "tipoagente", nullable = false)
    private Short  tipoAgente;

    public Agente(int id) {
        this.idagente = (long) id;
    }
}




