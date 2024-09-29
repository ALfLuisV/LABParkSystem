package com.parsystem.parksystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "agente")
@Data  // Lombok - Gera automaticamente getters, setters, equals, hashCode e toString
@AllArgsConstructor // Lombok - Gera construtor com todos os campos
@NoArgsConstructor  // Lombok - Gera construtor sem argumentos
public class Agente {
    @Id
    private String cnpj;

    @Column(nullable = false)
    private String nome;

    private String telefone;

    private String email;

    private byte tipoAgente;
}
