package com.basis.sge.repositorio;

import com.basis.sge.dominio.Evento;
import com.basis.sge.dominio.Inscricao;
import com.basis.sge.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepositorio extends JpaRepository<Inscricao, Integer> {

    void deleteAllByUsuario(Usuario usuario);
    void deleteAllByEvento (Evento evento);
    List<Inscricao> findByEvento(Evento evento);
    List<Inscricao> findByUsuario(Usuario usuario);

}
