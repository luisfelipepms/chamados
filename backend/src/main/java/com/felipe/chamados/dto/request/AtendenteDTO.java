package com.felipe.chamados.dto.request;

import com.felipe.chamados.enums.TimeAtendimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendenteDTO {

    @NotBlank
    private String nome;

    @NotNull
    private TimeAtendimento timeAtendimento;
}
