package com.felipe.chamados.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.chamados.dto.request.AtendimentoDTO;
import com.felipe.chamados.dto.response.AtendimentoResponseDTO;
import com.felipe.chamados.service.AtendimentoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    private AtendimentoService service;

    public AtendimentoController(AtendimentoService service){
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> listAll() {
        List<AtendimentoResponseDTO> resposta = service.listarTodos();

        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
    
    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> save(@RequestBody @Valid AtendimentoDTO dto) {
        
        AtendimentoResponseDTO salvo = service.criarAtendimento(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
    
    @PatchMapping("/finish/{id}")
    public ResponseEntity<AtendimentoResponseDTO> finish(@PathVariable Long id){
        AtendimentoResponseDTO salvo = service.finalizarAtendimento(id);

        return ResponseEntity.status(HttpStatus.OK).body(salvo);
    }

}
