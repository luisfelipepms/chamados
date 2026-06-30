package com.felipe.chamados.dto.request;

import com.felipe.chamados.enums.TimeAtendimento;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoDTO {

    @NotNull
    private TimeAtendimento timeAtendimento;

}
