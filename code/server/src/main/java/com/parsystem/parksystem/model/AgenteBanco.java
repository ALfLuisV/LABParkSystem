package com.parsystem.parksystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agentebanco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenteBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idagentebanco;

    // @ManyToOne
    @JoinColumn(name = "idagente", nullable = false)
    private Long  idagente;

    // @ManyToOne
    @JoinColumn(name = "codigobanco", nullable = false)
    private String  codigobanco;

    public AgenteBanco(int id) {
        this.idagentebanco = (long) id;
    }
}
