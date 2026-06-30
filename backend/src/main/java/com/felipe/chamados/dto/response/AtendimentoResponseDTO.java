package com.felipe.chamados.dto.response;

import java.time.LocalDateTime;

import com.felipe.chamados.entity.Atendente;
import com.felipe.chamados.enums.StatusAtendimento;
import com.felipe.chamados.enums.TimeAtendimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoResponseDTO {
    private Long id;
    private TimeAtendimento timeAtendimento;
    private StatusAtendimento statusAtendimento;
    private Atendente atendente;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataInicioAtendimento;
    private LocalDateTime dataFinalização;
}
