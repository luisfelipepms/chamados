package com.felipe.chamados.mapper;

import org.springframework.stereotype.Component;

import com.felipe.chamados.dto.response.AtendenteResponseDTO;
import com.felipe.chamados.entity.Atendente;

@Component
public class AtendenteMapper {
    public AtendenteResponseDTO toResponse(Atendente atendente){
        AtendenteResponseDTO responseDTO = new AtendenteResponseDTO();
        responseDTO.setId(atendente.getId());
        responseDTO.setNome(atendente.getNome());
        responseDTO.setAtivo(atendente.isAtivo());
        responseDTO.setTimeAtendimento(atendente.getTimeAtendimento());
        return responseDTO;
    }

    public Atendente toEntity(AtendenteResponseDTO dto){
        Atendente atendente = new Atendente();
        atendente.setId(dto.getId());
        atendente.setNome(dto.getNome());
        atendente.setAtivo(dto.isAtivo());
        atendente.setTimeAtendimento(dto.getTimeAtendimento());

        return atendente;
    }
}
