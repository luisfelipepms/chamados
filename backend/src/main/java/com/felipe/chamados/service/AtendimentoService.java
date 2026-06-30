package com.felipe.chamados.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felipe.chamados.dto.request.AtendimentoDTO;

import com.felipe.chamados.dto.response.AtendimentoResponseDTO;
import com.felipe.chamados.entity.Atendente;
import com.felipe.chamados.entity.Atendimento;
import com.felipe.chamados.enums.StatusAtendimento;
import com.felipe.chamados.enums.TimeAtendimento;

import com.felipe.chamados.mapper.AtendimentoMapper;
import com.felipe.chamados.repository.AtendenteRepository;
import com.felipe.chamados.repository.AtendimentoRepository;

import jakarta.transaction.Transactional;

@Service
public class AtendimentoService {
    private AtendimentoRepository repository;
    private AtendenteRepository atendenteRepository;
    private AtendimentoMapper mapper;

    public AtendimentoService(AtendimentoRepository repository, AtendenteRepository atendenteRepository, AtendimentoMapper mapper){
        this.mapper = mapper;
        this.atendenteRepository = atendenteRepository;
        this.repository = repository;
    }

    @Transactional
    public List<AtendimentoResponseDTO> listarTodos(){
        List<Atendimento> atendimentos = repository.findAll();

        List<AtendimentoResponseDTO> responseDTOs = atendimentos.stream().map(atendimento -> mapper.toResponse(atendimento)).toList();

        return responseDTOs;
    }

    @Transactional
    public AtendimentoResponseDTO criarAtendimento(AtendimentoDTO dto){

        TimeAtendimento time = determinarTime(dto.getTimeAtendimento());

        Optional<Atendente> atendenteDisponivel = buscarAtendenteDisponivel(time);

        Atendimento atendimento = new Atendimento();

        atendimento.setTimeAtendimento(time);
        atendimento.setDataCriacao(LocalDateTime.now());

        if(atendenteDisponivel.isPresent()){
            atendimento.setAtendente(atendenteDisponivel.get());
            atendimento.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
            atendimento.setDataInicioAtendimento(LocalDateTime.now());
        }else{
            atendimento.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
        }

        Atendimento salvo = repository.save(atendimento);

        return mapper.toResponse(salvo);
    }

    private TimeAtendimento determinarTime(TimeAtendimento assunto){

        if("Cartao".equalsIgnoreCase(assunto.toString())){
            return TimeAtendimento.CARTAO;
        }

        if("Emprestimo".equalsIgnoreCase(assunto.toString())){
            return TimeAtendimento.EMPRESTIMO;
        }

        return TimeAtendimento.OUTROS;
    }
    
    @Transactional
    private Optional<Atendente> buscarAtendenteDisponivel(TimeAtendimento time){
        return atendenteRepository
                .buscarDisponiveis(time.name(), StatusAtendimento.EM_ATENDIMENTO.name())
                .stream()
                .findFirst();
    }

    @Transactional
    public AtendimentoResponseDTO finalizarAtendimento(Long id){
        Atendimento atendimento = repository.findById(id).orElseThrow(() -> new RuntimeException("Atendimento não encontrado!"));
        
        TimeAtendimento time = atendimento.getTimeAtendimento();
        Atendente atendente = atendimento.getAtendente();

        atendimento.setStatusAtendimento(StatusAtendimento.FINALIZADO);
        atendimento.setDataFinalização(LocalDateTime.now());

        repository.save(atendimento);

        Optional<Atendimento> proximoAtendimento = buscarProximoAtendimento(time);

        if(proximoAtendimento.isPresent()){
            Atendimento prox = proximoAtendimento.get();
            prox.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
            prox.setAtendente(atendente);
            prox.setDataInicioAtendimento(LocalDateTime.now());
            repository.save(prox);
        }

        return mapper.toResponse(atendimento);
    }

    @Transactional
    private Optional<Atendimento> buscarProximoAtendimento(TimeAtendimento time){
        Optional<Atendimento> atendimento = repository.findFirstAtendimentoAguardando(time.toString());

        return atendimento;
    }

}
