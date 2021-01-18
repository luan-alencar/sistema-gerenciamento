package com.basis.sge.repositorio;

import com.basis.sge.dominio.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEventoRepositorio extends JpaRepository<TipoEvento, Integer> {


}