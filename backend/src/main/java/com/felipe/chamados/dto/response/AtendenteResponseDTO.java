package com.felipe.chamados.dto.response;


import com.felipe.chamados.enums.TimeAtendimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendenteResponseDTO {
    private Long id;
    private String nome;
    private TimeAtendimento timeAtendimento;
    private boolean ativo;
}
