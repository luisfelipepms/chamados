package com.felipe.chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felipe.chamados.entity.Atendente;

import java.util.List;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    List<Atendente> findByAtivoTrue();

    @Query(value = """
        SELECT a.*
        FROM atendentes a
        WHERE a.ativo = true
        AND a.time_atendimento = :time
        AND (
                SELECT COUNT(*)
                FROM atendimentos at
                WHERE at.atendente_id = a.id
                AND at.status_atendimento = :status
            ) < 3
        ORDER BY (
                SELECT COUNT(*)
                FROM atendimentos at
                WHERE at.atendente_id = a.id
                AND at.status_atendimento = :status
            ),
            a.id
        LIMIT 1
        FOR UPDATE SKIP LOCKED
    """, nativeQuery = true)
    List<Atendente> buscarDisponiveis(
            @Param("time") String time,
            @Param("status") String status
    );
}
