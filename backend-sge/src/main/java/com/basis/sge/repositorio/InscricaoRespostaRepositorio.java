package com.basis.sge.repositorio;

import com.basis.sge.dominio.InscricaoResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRespostaRepositorio extends JpaRepository<InscricaoResposta, Integer> {
}
