package com.basis.sge.repositorio;

import com.basis.sge.dominio.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepositorio extends JpaRepository<Inscricao, Integer> {

}
