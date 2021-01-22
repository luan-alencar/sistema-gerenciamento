package com.basis.sge.repositorio;

import com.basis.sge.dominio.EventoPergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoPerguntaRepositorio extends JpaRepository<EventoPergunta, Integer> {
}
