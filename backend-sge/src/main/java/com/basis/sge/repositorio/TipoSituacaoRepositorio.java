package com.basis.sge.repositorio;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.TipoSituacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSituacaoRepositorio extends JpaRepository<TipoSituacao, Integer> {
}
