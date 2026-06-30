package com.felipe.chamados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felipe.chamados.entity.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    @Query(value = """
            SELECT a.*
            FROM 
                atendimentos a 
            WHERE 
                a.status_atendimento = 'AGUARDANDO' AND a.time_atendimento = :time
            ORDER BY 
                a.data_criacao asc
            LIMIT 1
            """, nativeQuery = true)
    Optional<Atendimento> findFirstAtendimentoAguardando(@Param("time") String time);
}
