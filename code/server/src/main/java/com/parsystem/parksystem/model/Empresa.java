package com.parsystem.parksystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idempresa;

    @Column(nullable = false)
    private String cpfResponsavel;
}
