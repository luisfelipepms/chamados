package com.felipe.chamados.mapper;

import org.springframework.stereotype.Component;

import com.felipe.chamados.dto.response.AtendimentoResponseDTO;
import com.felipe.chamados.entity.Atendimento;

@Component
public class AtendimentoMapper {
    public AtendimentoResponseDTO toResponse(Atendimento atendimento){
        AtendimentoResponseDTO dto = new AtendimentoResponseDTO();
        dto.setId(atendimento.getId());
        dto.setAtendente(atendimento.getAtendente());
        dto.setDataCriacao(atendimento.getDataCriacao());
        dto.setDataFinalização(atendimento.getDataFinalização());
        dto.setDataInicioAtendimento(atendimento.getDataInicioAtendimento());
        dto.setStatusAtendimento(atendimento.getStatusAtendimento());
        dto.setTimeAtendimento(atendimento.getTimeAtendimento());

        return dto;
    }

}
