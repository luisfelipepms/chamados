package com.felipe.chamados.controller;

import org.springframework.web.bind.annotation.RestController;

import com.felipe.chamados.dto.request.AtendenteDTO;
import com.felipe.chamados.dto.response.AtendenteResponseDTO;
import com.felipe.chamados.service.AtendenteService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/atendente")
public class AtendenteController {
    
    private AtendenteService service;

    public AtendenteController(AtendenteService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<AtendenteResponseDTO> save(@RequestBody @Valid AtendenteDTO atendenteDTO) {
        AtendenteResponseDTO atendenteResponseDTO = service.cadastrar(atendenteDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteResponseDTO);
    }
    
    @GetMapping()
    public ResponseEntity<List<AtendenteResponseDTO>> listAll() {
        List<AtendenteResponseDTO> atendenteResponseDTO = service.listarTodos();
        
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteResponseDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AtendenteResponseDTO> findById(@PathVariable Long id) {
        AtendenteResponseDTO atendenteResponseDTO = service.buscarPorId(id);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteResponseDTO);
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<AtendenteResponseDTO>> listActives() {
        List<AtendenteResponseDTO> atendenteResponseDTO = service.listarAtivos();
        
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteResponseDTO);
    }
    
    @PatchMapping("/desativar/{id}")
    public ResponseEntity<AtendenteResponseDTO> deactivate(@PathVariable Long id) {
        AtendenteResponseDTO atendenteResponseDTO = service.desativar(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteResponseDTO);
    }
}
