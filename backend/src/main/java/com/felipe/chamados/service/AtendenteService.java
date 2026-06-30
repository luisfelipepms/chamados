package com.felipe.chamados.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felipe.chamados.dto.request.AtendenteDTO;
import com.felipe.chamados.dto.response.AtendenteResponseDTO;
import com.felipe.chamados.entity.Atendente;
import com.felipe.chamados.mapper.AtendenteMapper;
import com.felipe.chamados.repository.AtendenteRepository;

import jakarta.transaction.Transactional;

@Service
public class AtendenteService {
    private AtendenteRepository atendenteRepository;
    private AtendenteMapper mapper;

    public AtendenteService(AtendenteRepository atendenteRepository, AtendenteMapper mapper){
        this.atendenteRepository = atendenteRepository;
        this.mapper = mapper;
    }
    
    @Transactional
    public AtendenteResponseDTO cadastrar(AtendenteDTO dto){
        Atendente atendente = new Atendente();

        atendente.setNome(dto.getNome());

        atendente.setTimeAtendimento(dto.getTimeAtendimento());

        atendente.setAtivo(true);

        Atendente salvo = atendenteRepository.save(atendente);

        return mapper.toResponse(salvo);
    }

    @Transactional
    public List<AtendenteResponseDTO> listarAtivos(){

        List<Atendente> response = atendenteRepository.findByAtivoTrue();

        return response.stream().map(atendente -> mapper.toResponse(atendente)).toList();
    }

    @Transactional
    public List<AtendenteResponseDTO> listarTodos(){

        List<Atendente> response = atendenteRepository.findAll();

        return response.stream().map(atendente -> mapper.toResponse(atendente)).toList();
    }

    @Transactional
    public AtendenteResponseDTO buscarPorId(Long id){
        Atendente resposta = atendenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Atendente não encontrado!"));
        return mapper.toResponse(resposta);
    }

    @Transactional
    public AtendenteResponseDTO desativar(Long id){
        Atendente atendente = atendenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Atendente não enconrado"));
        atendente.setAtivo(false);
        Atendente salvo = atendenteRepository.save(atendente);
        return mapper.toResponse(salvo);
    }

}
