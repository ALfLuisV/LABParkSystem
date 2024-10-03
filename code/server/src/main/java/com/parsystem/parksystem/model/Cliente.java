package com.parsystem.parksystem.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "cliente")
@Data 
@AllArgsConstructor
@NoArgsConstructor
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

    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idendereco")
    private Long idendereco;
}