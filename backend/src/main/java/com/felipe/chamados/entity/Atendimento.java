package com.felipe.chamados.entity;

import java.time.LocalDateTime;

import com.felipe.chamados.enums.StatusAtendimento;
import com.felipe.chamados.enums.TimeAtendimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "atendimentos")
@Getter
@Setter
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TimeAtendimento timeAtendimento;

    @Enumerated(EnumType.STRING)
    private StatusAtendimento statusAtendimento;

    @ManyToOne
    @JoinColumn(name="atendente_id")
    private Atendente atendente;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataInicioAtendimento;

    private LocalDateTime dataFinalização;

}
