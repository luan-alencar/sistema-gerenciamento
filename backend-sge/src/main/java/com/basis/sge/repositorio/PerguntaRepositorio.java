package com.basis.sge.repositorio;

import com.basis.sge.dominio.Pergunta;
import com.basis.sge.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepositorio extends JpaRepository<Usuario, Integer> {



}
